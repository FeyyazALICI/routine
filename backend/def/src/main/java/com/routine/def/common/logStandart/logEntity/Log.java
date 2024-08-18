package com.routine.def.common.logStandart.logEntity;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "log")
public class Log implements Serializable
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "status_message")
	private int status_message;
	@Column(name = "record_date")
	private Timestamp record_date;
	@Column(name = "http_status")
	private String http_status;
	@Column(name = "request_type")
	private String request_type;
	@Column(name = "data")
	private String data;
	@Column(name = "user")
	private String user;


	public Log(
		Long id,
		int status_message,
		Timestamp record_date,
		String http_status,
		String data,
		String user
	){
		this.id = id;
		this.status_message = status_message;
		this.record_date = record_date;
		this.http_status = http_status;
		this.data = data;
		this.user = user;
	}
}
