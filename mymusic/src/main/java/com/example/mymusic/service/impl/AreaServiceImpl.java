package com.example.mymusic.service.impl;


import com.example.mymusic.common.ErrorCode;
import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.common.Page;
import com.example.mymusic.common.Random2Utils;
import com.example.mymusic.common.pages.IPage;
import com.example.mymusic.dao.IAreaDao;
import com.example.mymusic.entity.Area;
import com.example.mymusic.entity.dto.AreaDTO;
import com.example.mymusic.entity.param.AreaParam;
import com.example.mymusic.service.IAreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类说明：地区表接口实现
 *
 * @author 黄丽星
 * @date 2018年9月10日 13时23分17秒
 */
@Service("areaService")
public class AreaServiceImpl implements IAreaService {

	private static Map<String, Object> areaCaches = new ConcurrentHashMap<String, Object>();
	
    private static Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);
    
    @Resource
    private IAreaDao areaDao;
    

    
    @Override
    public Integer addArea(Area area) throws FdServiceException {
        area.setAreaNo(Random2Utils.buildSn(""));
        try {

            return areaDao.addArea(area);
        } catch (Exception e) {
            logger.error("添加地区[" + area + "]失败", e);
            throw new FdServiceException(ErrorCode.INSERT_EXCEPTION, "添加地区失败", e);
        }
    }

    @Override
    public Integer delArea(String areaNo) throws FdServiceException {
        try {

            return areaDao.delArea(areaNo);
        } catch (Exception e) {
            logger.error("删除地区[" + areaNo + "]失败", e);
            throw new FdServiceException(ErrorCode.UPDATE_EXCEPTION, "删除地区失败", e);
        }
    }


    @Override
    public Integer updateArea(Area area) throws FdServiceException {
        try {
            return areaDao.updateArea(area);
        } catch (Exception e) {
            logger.error("修改地区[" + area + "]失败", e);
            throw new FdServiceException(ErrorCode.UPDATE_EXCEPTION, "修改地区失败", e);
        }
    }


    @Override
    public Area queryAreaByAreaNo(String areaNo) throws FdServiceException {
        try {
            return areaDao.queryAreaByAreaNo(areaNo);
        } catch (Exception e) {
            logger.error("根据编号查询地区[" + areaNo + "]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION, "根据编号查询地区失败", e);
        }
    }
    @Override
    public Area queryAreaByAreaId(String areaId) throws FdServiceException {
        try {
            return areaDao.queryAreaByAreaId(areaId);
        } catch (Exception e) {
            logger.error("根据编号查询地区[" + areaId + "]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION, "根据编号查询地区失败", e);
        }
    }



    @Override
    public Long queryAreaCountByCondition(AreaParam areaParam) throws FdServiceException {
        try {
        	areaParam.setStatus("0");
            return areaDao.queryAreaCountByCondition(areaParam);
        } catch (Exception e) {
            logger.error("统计查询地区[" + areaParam + "]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION, "统计查询地区失败", e);
        }
    }


    @Override
    public IPage<Area> queryAreaByCondition(AreaParam areaParam) throws FdServiceException {
        List<Area> list = null;
        try {
        	areaParam.setStatus("0");
            Long count = areaDao.queryAreaCountByCondition(areaParam);
            if (count != null && count > 0) {
                list = areaDao.queryAreaByCondition(areaParam);
            }
            return new Page<Area>(list, count, areaParam);
        } catch (Exception e) {
            logger.error("根据条件查询地区[" + areaParam + "]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION, "根据条件查询地区失败", e);
        }
    }

    @Override
    public List<Area> queryAreaByConditions(AreaParam areaParam) throws FdServiceException {
        List<Area> list = null;
        try {
             list = areaDao.queryAreaByCondition(areaParam);
            return list;
        } catch (Exception e) {
            logger.error("根据条件查询地区[" + areaParam + "]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION, "根据条件查询地区失败", e);
        }
    }

    @Override
    public List<Area> queryAreaByConditionApp(AreaParam areaParam) throws FdServiceException {
        List<Area> list = null;
        List<Area> cityList =null;
        areaParam.setAreaType(1);
        AreaParam areaParam2 =new AreaParam();
        AreaParam areaParam3 =new AreaParam();
        areaParam.setLimit(10000);
        areaParam2.setLimit(10000);
        areaParam3.setLimit(10000);
        try {
            /**取得省**/
            list = areaDao.queryAreaByConditionApp(areaParam);
            for (Area area:list) {
                areaParam2.setParentAreaNo(area.getAreaNo());
                cityList =areaDao.queryAreaByConditionApp(areaParam2);
                for (Area city:cityList) {
                    areaParam3.setParentAreaNo(city.getAreaNo());
                    city.setChildren(areaDao.queryAreaByConditionApp(areaParam3));
                }
                area.setChildren(cityList);
            }
            return list;
        } catch (Exception e) {
            logger.error("根据条件查询地区[" + areaParam + "]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION, "根据条件查询地区失败", e);
        }
    }

    @Override
    public Long queryAllAreaCountByCondition(AreaParam areaParam) {
        return null;
    }

    @Override
    public IPage<AreaDTO> queryAllAreaByCondition(AreaParam areaParam) throws FdServiceException {
        List<AreaDTO> list = null;
        try {
            areaParam.setStatus("0");
            Long count = areaDao.queryAllAreaCountByCondition(areaParam);
            if (count != null && count > 0) {
                list = areaDao.queryAllAreaByCondition(areaParam);
            }
            return new Page<AreaDTO>(list, count, areaParam);
        } catch (Exception e) {
            logger.error("根据条件查询地区[" + areaParam + "]失败", e);
            throw new FdServiceException(ErrorCode.QUERY_EXCEPTION, "根据条件查询地区失败", e);
        }
    }

    @Override
    public Area getAreaByAreaName(AreaParam areaParam) {
        return null;
    }



}
