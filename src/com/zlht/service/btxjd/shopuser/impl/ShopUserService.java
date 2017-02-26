package com.zlht.service.btxjd.shopuser.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.zlht.dao.DaoSupport;
import com.zlht.entity.Page;
import com.zlht.util.PageData;
import com.zlht.service.btxjd.shopuser.ShopUserManager;

/** 
 * 说明： 商城用户
 * 创建人：ZLHT
 * 创建时间：2016-12-04
 * @version
 */
@Service("shopuserService")
public class ShopUserService implements ShopUserManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("ShopUserMapper.save", pd);
	}

	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByUserId(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ShopUserMapper.findByUserId", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("ShopUserMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("ShopUserMapper.edit", pd);
	}

	public void editquota(PageData pd)throws Exception{
		dao.update("ShopUserMapper.editquota", pd);
	}

	public void editusedquota(PageData pd)throws Exception{
		dao.update("ShopUserMapper.editusedquota", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ShopUserMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ShopUserMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */

	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ShopUserMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ShopUserMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

