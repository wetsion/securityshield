package site.wetsion.security.shield.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import site.wetsion.security.shield.domain.SysRole;

import java.util.List;
import java.util.Set;

/**
 * @author weixin
 * @version 1.0
 * @CLassName SysRoleMapper
 * @date 2020/1/9 3:39 PM
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据用户id获取用户拥有的所有角色
     *
     * @param userId
     * @author weixin
     * @date 4:02 PM 2020/1/9
     * @return java.util.Set<site.wetsion.security.shield.domain.SysRole>
     **/
    @Select("SELECT sr.* FROM study.sys_role sr " +
            "left join study.sys_user_role sur on sur.role_id = sr.id " +
            "where sur.user_id = #{userId}")
    Set<SysRole> getAllRolesByUserId(Long userId);

    @Select("select r.* from sys_role r left join sys_role_permission srp " +
            "on r.id = srp.role_id where srp.permission_id = #{permissionId}")
    List<SysRole> findRoleByPermission(Long permissionId);
}
