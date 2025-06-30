package com.kh.domain;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter 
@Setter 
@ToString 
public class MemberAuth {
	private int userNo; 
	private String auth;
}
