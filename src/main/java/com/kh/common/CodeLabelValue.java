package com.kh.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@RequiredArgsConstructor:final 필드와 @NonNull 필드에 대한 생성자 자동 생성 
@RequiredArgsConstructor
@Getter
//상수는 생성자를 통해서만 값할당 가능@Setter
@ToString
public class CodeLabelValue {
	// value는 프로그램 내부에서 식별용으로 사용하고, label은 사용자에게 보여주는 친절한 설명
	// value: DB에 저장된 코드값
	private final String value;
	// label: UI나 폼에 보여줄 텍스트
	private final String label;

	// <form:select path="groupCode" items="${groupCodeList}" itemValue="value"
	// itemLabel="label" />
	// 이 때 groupCodeList는 List<CodeLabelValue> 타입이고,

	//각각의 객체는 value="M01" / label="회원등급" 이런 식이죠.

	//그래서 드롭다운에는 "회원등급"이 보이고, 선택하면 내부적으로는 "M01"이 서버로 전송
}
