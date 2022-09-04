package com.example.mymusic.dao;


import com.example.mymusic.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 管理员(Admin)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-07 09:52:18
 */
@Repository
public interface AdminDao {

    /**
     * 通过ID查询单条数据
     *
     * @param adminNo 主键
     * @return 实例对象
     */
    Admin queryAdminByAdminNo(String adminNo);

    /**
     * 通过ID查询单条数据
     *
     * @param adminNo 主键
     * @return 实例对象
     */
    Admin queryAdminById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Admin> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param admin 实例对象
     * @return 对象列表
     */
    List<Admin> queryAll(@Param("offset") int offset, @Param("limit") int limit,Admin admin);

    List<Admin> queryAdminByCondition(Admin admin);

    Long    queryAllCount();

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 影响行数
     */
    int addAdmin(Admin admin);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Admin> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Admin> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Admin> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Admin> entities);

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 影响行数
     */
    int updateAdmin(Admin admin);

    /**
     * 通过主键删除数据
     *
     * @param adminNo 主键
     * @return 影响行数
     */
    int deleteByAdminNo(String adminNo);


    /**
     * 假删除
     *
     * @param adminNo 主键
     * @return 是否成功
     */
    int deleteAdminByAdminNo(String adminNo);

    /**
     * @Author 劳威锟
     * @Description //方法说明： 登录
     * @Date 11:28 2022/1/19
     * @Param
     * @return
     **/
    Admin login(Admin admin);

}

