package cn.edu.csu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.util.Pair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.csu.common.TemperatureProcess;
import cn.edu.csu.common.TemperatureProcessAndTime;
import cn.edu.csu.dao.TemperatureDataDao;
import cn.edu.csu.dao.TransportProcessDao;
import cn.edu.csu.pojo.CommodityTransport;
import cn.edu.csu.pojo.TemperatureData;
import cn.edu.csu.pojo.TransportProcess;
import cn.edu.csu.service.TemperatureDataService;
import cn.edu.csu.util.TimeUtil;

@Service
public class TemperatureDataServiceImpl implements TemperatureDataService{

	@Autowired
	private TemperatureDataDao tempDataDao;
	
	@Autowired
	private TransportProcessDao transProcDao;
	
	public TemperatureDataDao getTempDataDao() {
		return tempDataDao;
	}

	public void setTempDataDao(TemperatureDataDao tempDataDao) {
		this.tempDataDao = tempDataDao;
	}
	
	public TransportProcessDao getTransProcDao() {
		return transProcDao;
	}

	public void setTransProcao(TransportProcessDao transProcDao) {
		this.transProcDao = transProcDao;
	}
	
	@Override
	public TemperatureProcessAndTime getTemperatureProcess(CommodityTransport comdtyTrans,int timeInterval) {
		
		TemperatureProcessAndTime tpt = new TemperatureProcessAndTime();
		TemperatureProcess tp;
		String t;
		String[] simpleTempData;
		String tempString;
		
		//读取某商品配送批次在数据库temp_data表，和trans_proc表中的相应信息
		List<TemperatureData> tempDataList = tempDataDao.findByCommodityTransportId(comdtyTrans.getId());
		List<TransportProcess> transProcList = transProcDao.findByCommodityTransportId(comdtyTrans.getId());
		
		//计算每个阶段花费的时间
		List<Pair<String,Integer>> procIntervalList = getProcessInterval(transProcList);
		
		//第一个节点单独处理，因为第一个节点没有“上一站的停留阶段”
		//后面的节点将成对处理，因为每条温度记录包括（上一站的停留阶段，与上一站到本站的运输阶段）
		tempString = tempDataList.get(0).getTemperature();
		simpleTempData = tempString.split(",");
		int cnt = procIntervalList.get(0).getValue()/ timeInterval;
		String process = procIntervalList.get(0).getKey();
		
		//取前温度字符串拆分成的数组的0->cnt个数据，将温度按时间从旧到新排列。
		t="";
		for(int j = cnt-1; j>0; j--){
			t+=simpleTempData[j];
			t+=",";
		}
		t+=simpleTempData[0];
		tp = new TemperatureProcess();
		tp.setProcess(process);
		tp.setTemperature(t);
		tpt.add(tp);
		
		//后续节点成对处理
		for(int i = 1,k=1;i<tempDataList.size();i++,k+=2){
			tempString = tempDataList.get(i).getTemperature();
			simpleTempData = tempString.split(",");
			int cnt1 = procIntervalList.get(k).getValue()/ timeInterval;
			int cnt2 = procIntervalList.get(k+1).getValue()/ timeInterval;
			String stop = procIntervalList.get(k).getKey();
			String run = procIntervalList.get(k+1).getKey();
			//取n2-n1
			t="";
			for(int j = cnt1+cnt2-1; j >cnt2  ; j--){
				t+=simpleTempData[j];
				t+=",";
			}
			t+=simpleTempData[cnt2];
			tp = new TemperatureProcess();
			tp.setProcess(stop);
			tp.setTemperature(t);
			tpt.add(tp);
			//取0-n1
			t="";
			for(int j = cnt2-1; j>0; j--){
				t+=simpleTempData[j];
				t+=",";
			}
			t+=simpleTempData[0];
			tp = new TemperatureProcess();
			tp.setProcess(run);
			tp.setTemperature(t);
			tpt.add(tp);
		}
		long time = tempDataList.get(tempDataList.size()-1).getSubmitTime().getTime();
		tpt.setLastSubmitTimeOrFailInfo(TimeUtil.longToDate(time));
		return tpt;
	}
	
	private List<Pair<String,Integer>> getProcessInterval(List<TransportProcess> transProcList){
		List<Pair<String,Integer>> processIntervalList = new ArrayList<Pair<String,Integer>>();
		Date LastArrTime=null;
		String LastArrPlace = null;
		for(int i = 0;i<transProcList.size();i++){
			//取出：1.出发站 2.到达站 3.出发时间 4.到达时间
			Date deptTime = transProcList.get(i).getDepartureTime();
			Date arrTime = transProcList.get(i).getArrivalTime();
			String deptPlace = transProcList.get(i).getDeparturePlace();
			String arrPlace = transProcList.get(i).getArrivalPlace();
			//计算在上一站的停留时间，单位为min(如果不是始发站)
			if(LastArrTime!=null){
				int interval = (int)TimeUtil.millisecondsToMinutes(deptTime.getTime()-LastArrTime.getTime());
				//记录路径与时间间隔
				processIntervalList.add(new Pair<String,Integer>(LastArrPlace,interval));
			}
			//计算运输途中经过的时间，单位为min,并记录路径与时间间隔
			int interval = (int)TimeUtil.millisecondsToMinutes(arrTime.getTime()-deptTime.getTime());
			processIntervalList.add(new Pair<String,Integer>(deptPlace+"-"+arrPlace,interval));
			//保存上一站点名称和到达时间
			LastArrTime = arrTime;
			LastArrPlace= arrPlace;
		}
		return processIntervalList;
	}

	@Override
	public void inputTemperatureData(TemperatureData tempData) {
		tempDataDao.add(tempData.getCommodityTransport().getId(), tempData.getTemperature());
	}

	@Override
	public Date getLastSubmitTime(CommodityTransport comdtyTrans) {
		List<TemperatureData> list = tempDataDao.findByCommodityTransportId(comdtyTrans.getId());
		return list.get(list.size()-1).getSubmitTime();
	}
	
	
	
	
//    Iterator<String> it = map.keySet().iterator();  
//    while (it.hasNext()) {  
//    	String key = it.next();
//
//        System.out.println(key+"\t"+map.get(key));  
//    }  
	
//	for(int i = 0;i<tempDataList.size();i++){
//	System.out.println("温度："+tempDataList.get(i).getTempture());
//	System.out.println("时间："+tempDataList.get(i).getSubmitTime());
//	System.out.println("");
//}
//
//for(int i = 0;i<transProcList.size();i++){
//	System.out.println("出发站："+transProcList.get(i).getDeparturePlace());
//	Timestamp deptTime = transProcList.get(i).getDepartureTime();
//	System.out.println("出发时间："+deptTime);
//	System.out.println("到达站："+transProcList.get(i).getArrivalPlace());
//	Timestamp arrTime = transProcList.get(i).getArrivalTime();
//	System.out.println("到达时间："+arrTime);
//	long interval = TimeUtil.millisecondsToMinutes(arrTime.getTime()-deptTime.getTime());
//	System.out.println("时间差："+ interval + "分钟");
//	System.out.println();
//}
	
}
