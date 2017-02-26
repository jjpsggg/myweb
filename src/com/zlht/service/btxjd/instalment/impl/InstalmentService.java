package com.zlht.service.btxjd.instalment.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.zlht.dao.DaoSupport;
import com.zlht.entity.Page;
import com.zlht.util.PageData;
import com.zlht.service.btxjd.instalment.InstalmentManager;

/** 
 * 说明： 分期费率管理
 * 创建人：ZLHT
 * 创建时间：2016-12-11
 * @version
 */
@Service("instalmentService")
public class InstalmentService implements InstalmentManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("InstalmentMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("InstalmentMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("InstalmentMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("InstalmentMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("InstalmentMapper.listAll", pd);
	}

	/**列表(期次)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listTerm(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("InstalmentMapper.listTerm", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("InstalmentMapper.findById", pd);
	}

	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByPd(PageData pd)throws Exception{
		return (PageData)dao.findForObject("InstalmentMapper.findByPd", pd);
	}

	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("InstalmentMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

