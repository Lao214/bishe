package com.example.mymusic.service;

import com.example.mymusic.common.pages.IPage;
import com.example.mymusic.entity.Area;
import com.example.mymusic.entity.dto.AreaDTO;
import com.example.mymusic.entity.param.AreaParam;

import java.util.List;

/**
 * 接口说明：账户表dao
 * @author 黄丽星
 * @date 2018年09月10日
 */
public interface IAreaService {

	/**
	 * 备注： Integer
	 * 方法说明：添加地区dao
	 * @author 聂枫
	 * @date 2018年09月18日 11时58分55秒
	 * @return
	 */
	public Integer addArea(Area area);

	/**
	 * 备注： Integer
	 * 删除地区dao
	 * @author 聂枫
	 * @date 2018年09月18日 11时58分55秒

	 * @return
	 */
	public Integer delArea(String areaNo);

	/**
	 * 备注： Integer
	 * 修改地区dao
	 * @author 聂枫
	 * @date 2018年09月18日 11时58分55秒
     * @return
	 */
	public Integer updateArea(Area area);

	/**
	 * 备注： Integer
	 * 通过编号查询地区dao
	 * @author 聂枫
	 * @date 2018年09月18日 11时58分55秒
	 * @return Long
	 */
	public Area queryAreaByAreaNo(String areaNo);

	/**
	 * 备注： Integer
	 * 通过编号查询地区dao
	 * @author 王诚
	 * @date 2018年09月25

	 * @return Long
	 */
	public Area queryAreaByAreaId(String areaId);

	/**
	 * 备注： Integer
	 * 统计查询地区dao
	 * @author 聂枫
	 * @date 2018年09月18日 11时58分55秒
	 * @return Long
	 */
	public Long queryAreaCountByCondition(AreaParam areaParam);

	/**
	 * 备注： Integer
	 * 分页查询地区dao
	 * @author 聂枫
	 * @date 2018年09月18日 11时58分55秒

	 * @return List<Area>
	 */
	public IPage<Area> queryAreaByCondition(AreaParam areaParam);
	public List<Area> queryAreaByConditions(AreaParam areaParam);

	public List<Area> queryAreaByConditionApp(AreaParam areaParam);
	/**
	 * 备注： Integer
	 * 统计查询地区dao
	 * @author 聂枫
	 * @date 2018年09月18日 11时58分55秒
	 * @return Long
	 */
	public Long queryAllAreaCountByCondition(AreaParam areaParam);

	/**
	 * 备注： Integer
	 * 分页查询地区dao
	 * @author 聂枫
	 * @date 2018年09月18日 11时58分55秒
	 * @return List<Area>
	 */
	public IPage<AreaDTO> queryAllAreaByCondition(AreaParam areaParam);
	/**
	 * 查询地区
	 * @return Area
	 * @author 刘逸飞
	 * @date 2018年09月18日 11时58分55秒
	 */

	public Area getAreaByAreaName(AreaParam areaParam);



}
