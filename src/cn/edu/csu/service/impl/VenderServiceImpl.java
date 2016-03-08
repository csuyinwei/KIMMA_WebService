package cn.edu.csu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.csu.dao.VenderDao;
import cn.edu.csu.pojo.Vender;
import cn.edu.csu.service.VenderService;


@Service
public class VenderServiceImpl implements VenderService {
	@Autowired
	private VenderDao venderDao;
	
	public VenderDao getVenderDao() {
		return venderDao;
	}

	public void setVenderDao(VenderDao venderDao) {
		this.venderDao = venderDao;
	}

	@Override
	public void inputVender(Vender vender) {
		venderDao.save(vender);
	}

	@Override
	public void updateVender(Vender vender) {
		venderDao.update(vender);
	}

	@Override
	public Vender getVenderByNumber(String number) {
		return venderDao.findVenderByNumber(number);
	}


	@Override
	public List<Vender> listAll() {
		return venderDao.listAll();
	}

	@Override
	public void deleteByVenderNumber(String venderNumber) {
		venderDao.deleteVenderByNumber(venderNumber);
	}

	@Override
	public List<String> listAllVenderNumber() {
		return venderDao.listAllVenderNumber();
	}

	@Override
	public long getSearchCount(String search) {
		return venderDao.getSearchCount(search);
	}

	@Override
	public List<Vender> searchPagination(String search, int pageNum, int pageSize) {
		return venderDao.searchPagination(search, pageNum, pageSize);
	}
}
