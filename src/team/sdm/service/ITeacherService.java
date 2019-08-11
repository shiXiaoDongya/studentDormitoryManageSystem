package team.sdm.service;

import java.util.List;

import team.sdm.po.Teacher;

public interface ITeacherService {

	List<Teacher> getTeacherList();

	List<Long> getTeacherByBuidingId(int id);

	Teacher login(String name, String password);

	List<Teacher> getTeacherList1(String teacher_name, int start, int rows);

	int teacherCount(String teacher_name);

	int addteacher(Teacher teacher);

	int editTeacher(Teacher teacher);

	boolean deleteTeacher(long parseLong);

	List<Teacher> teacherList(Teacher teacher, int i, int j);

	
}
