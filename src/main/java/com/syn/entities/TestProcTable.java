package com.syn.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@NamedNativeQueries({
	@NamedNativeQuery(
		name = "sp_getTestProc",
		query = "SELECT * FROM get_test_proc_res(:t_id)",
		resultClass = TestProcTable.class
	),
	@NamedNativeQuery(
		name = "sp_insertUser",
		query = "SELECT * FROM insert_test_proc(:t_name)",
		resultClass = TestProcTable.class
	)
})


@Entity
@Table(name = "test_proc_table")
public class TestProcTable {
	@Id
	@Column(name = "tab_id", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "tab_name")
	private String name;
	
	public Integer getId() {
		return id;
	}
 
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}

