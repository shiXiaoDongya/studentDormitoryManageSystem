package team.sdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.sdm.mapper.DormMapper;
import team.sdm.mapper.StudentMapper;
import team.sdm.po.Dorm;
import team.sdm.po.Student;
import team.sdm.po.StudentVo;
import team.sdm.po.Student_lack;
import team.sdm.service.IStudentService;

@Service
public class StudentService implements IStudentService {
	@Autowired
	private StudentMapper studentMapper;



	@Override
	public Student login(String name, String password) {
		// TODO Auto-generated method stub
		return studentMapper.login(name, password);
	}

	@Override
	public List<Student_lack> getStudentLackList(String s_name, int page, int rows) {
		// TODO Auto-generated method stub
		int start = (page - 1) * rows;
		return studentMapper.getStudentLackList(s_name, start, rows);
	}

	@Override
	public int getStudentLackCount() {
		// TODO Auto-generated method stub
		return studentMapper.getStudentLackCount();
	}

	@Override
	public Student_lack getStudent_lack(long id) {
		// TODO Auto-generated method stub
		return studentMapper.getStudent_lack(id);
	}

	@Override
	public List<String> qryIfOfFlag(String student_name) {
		// TODO Auto-generated method stub
		return studentMapper.qryIfOfFlag(student_name);
	}

	@Override
	public int updateLack(long id) {
		// TODO Auto-generated method stub
		return studentMapper.updateLack(id);
	}

	@Override
	public Student getStudent(Student stu) {
		// TODO Auto-generated method stub
		return studentMapper.getStudent(stu);
	}

	@Override
	public boolean deleteStudentAndStudentDorm(long student_id) {
		if (studentMapper.deleteStudent(student_id)) {
			studentMapper.deleteStudent_Dorm(student_id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public StudentVo findStudent(StudentVo vo) {
		// TODO Auto-generated method stub
		return studentMapper.findStudent(vo);
	}

	@Override
	public List<StudentVo> studentList(StudentVo vo, int page, int rows) {
		return studentMapper.studentList(vo, page, rows);
	}

	@Override
	public int StudentCount(StudentVo vo) {
		return studentMapper.studentCount(vo);
	}

	@Override
	public boolean addStudent(StudentVo vo) {
		if (vo.getStudent_state() == null) {
			vo.setStudent_state("未入住");
		}
		return studentMapper.addStudent(vo);
	}

	@Override
	public boolean editStudent(StudentVo vo) {
		return studentMapper.updateStudentVo(vo);
	}

	@Override
	public List<StudentVo> studentLackList(StudentVo vo, int page, int rows) {
		// TODO Auto-generated method stub
		return studentMapper.studentLackList(vo, page, rows);
	}

	@Override
	public int studentLackCount() {
		// TODO Auto-generated method stub
		return studentMapper.studentLackCount();
	}

	@Override
	public boolean inDorm(StudentVo vo, Dorm dorm) {
		
		if (studentMapper.inDorm(vo, dorm) > 0) {
			vo.setStudent_state("入住");
			vo.setStudent_building(dorm.getDorm_building());
			vo.setStudent_dorm(dorm.getDorm_name());
			studentMapper.updateStudentVo(vo);
			return true;
		}
		return false;

	}

	@Override
	public List<Student_lack> getAllStudentLack() {
		// TODO Auto-generated method stub
		return studentMapper.getAllStudentLack();
	}

	@Override
	public int updateLackFlag(long id) {
		// TODO Auto-generated method stub
		return studentMapper.updateLackFlag(id);
	}

	@Override
	public int addStudentLack(Student_lack stuLack) {
		// TODO Auto-generated method stub
		return studentMapper.addStudentLack(stuLack);
	}

	@Override
	public int changeStudentDorm(long dorm_id, long student_id) {
		// TODO Auto-generated method stub
		return studentMapper.changeStudentDorm(dorm_id, student_id);
	}

	@Override
	public int changeStudentByChangeDorm(long student_id, long building_id, long dorm_id, String remark) {
		// TODO Auto-generated method stub
		return studentMapper.changeStudentByChangeDorm(student_id, building_id, dorm_id, remark);
	}

}
