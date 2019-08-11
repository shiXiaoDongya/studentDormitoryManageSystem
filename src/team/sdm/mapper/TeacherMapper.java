package team.sdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import team.sdm.po.Teacher;

public interface TeacherMapper {

	List<Teacher> getTeacherList();

	List<Long> getTeacherByBuidingId(int id);

	Teacher login(String name, String password);

	List<Teacher> getTeacherList1(@Param("teacher_name") String teacher_name, @Param("start")int start, @Param("rows")int rows);


	int getTeacherCount(@Param("teacher_name")String teacher_name);

	int addTeacher(Teacher teacher);

	int editTeacher(Teacher teacher);

	boolean deleteTeacher(long teacher_id);

	List<Teacher> exportTeacher(@Param("Teacher") Teacher teacher, @Param("page") int page,
			@Param("rows") int rows);

	

}
