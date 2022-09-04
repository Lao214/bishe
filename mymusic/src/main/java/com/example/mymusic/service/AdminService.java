package com.example.mymusic.service;


import com.example.mymusic.common.pages.IPage;
import com.example.mymusic.entity.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 管理员(Admin)表服务接口
 *
 * @author makejava
 * @since 2022-01-07 09:52:19
 */
public interface AdminService {

    /**
     * 通过ID查询单条数据
     *
     * @param adminNo 主键
     * @return 实例对象
     */
    Admin queryAdminByAdminNo(String adminNo);

    Admin queryAdminById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Admin> queryAllByLimit(int offset, int limit);


    Long    queryAllCount();



    /**
     * 通过实体作为筛选条件查询
     *
     * @param admin 实例对象
     * @return 对象列表
     */
    List<Admin> queryAll(int offset,int limit, Admin admin);

    List<Admin> queryAdminByCondition(Admin admin);

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    Admin insert(Admin admin);

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    Admin updateAdmin(Admin admin);

    /**
     * 通过主键删除数据
     *
     * @param adminNo 主键
     * @return 是否成功
     */
    boolean deleteByAdminNo(String adminNo);

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
