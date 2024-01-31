package com.example.demo;

import java.util.HashMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;

//java 객체를 json 형태로 바꿔준다.
//http://localhost:8080/getUserInfo
//기본 포트번호가 8080
//오라클을 express버전을 설치하면 홈페이지를 만들어서 내부에서 8080쓰고 있어서 
//포트번호가 충돌날 수 있다.

@CrossOrigin("*")
@RestController
@RequestMapping(value="")
// /hello/ ~~~~ 이 컨트롤러가 다 처리한다.
public class HelloContoller {
	
	
	//단축키 - ctrl-shift-o
	@GetMapping("/getUserInfo")
	public HashMap<String, String> getUserInfo()
	{
		HashMap<String, String> map = new HashMap<String, String>();
		//컬렉션클래스, 배열, Map, json, SortedList...
		//배열의 요소는 index를 통해 읽고 쓸 수 있다.
		//HashpMap, Dictionay, json 동일한 구조
		//키와 값 쌍으로 구성되는 데이터를 저장해서 데이터를 읽고 쓸때 키값을 찾아서 읽고 쓰기 한다.
		//{"name":"홍길동", "phone":"010-9000-0001", "address":"서울시"}
		
		map.put("name", "홍길동");
		map.put("phone", "010-9000-0001");
		map.put("address", "서울시 관악구");
		
		return map;
	}
	
	//정보를 주고 받는 방식 - get방식
	// /getUserInfo?userid=test&username=홍길동
	
	// 새로운 방식
	// /getUserInfo/test
	
	// post방식은 => form태그에 method = "POST"로 바꿔야 한다.
	// add1?x=5&y=7 {x:5, y:7, result:12} - get
	// add2/5/7 {x:5, y:7, result:12} - get
	// add3     {x:5, y:7, result:12} - post
	
	@GetMapping("/add1")
	public HashMap<String, Object> add1(HttpServletRequest request,
			@RequestParam("x") int x, 
			@RequestParam("y") int y)
	{
		//HttpRequestServlet 객체에 담아온다.
		//int x = Integer.parseInt(request.getParameter("x"));
		HashMap<String, Object> map = new HashMap<String, Object> ();
		map.put("x", x);
		map.put("y", y);
		map.put("result", x+y);
		
		return map;
		
	}
	
	
	//add2/5/7
	@GetMapping("/add2/{x}/{y}")
	public HashMap<String, Object> add2(HttpServletRequest request,
			@PathVariable("x") int x, 
			@PathVariable("y") int y)
	{
		//HttpRequestServlet 객체에 담아온다.
		//int x = Integer.parseInt(request.getParameter("x"));
		HashMap<String, Object> map = new HashMap<String, Object> ();
		map.put("x", x);
		map.put("y", y);
		map.put("result", x+y);
		
		return map;
	}
	
	
	@PostMapping("/add3")
	public HashMap<String, Object> add3(HttpServletRequest request,
			@RequestParam("x") int x, 
			@RequestParam("y") int y)
	{
		//HttpRequestServlet 객체에 담아온다.
		//int x = Integer.parseInt(request.getParameter("x"));
		HashMap<String, Object> map = new HashMap<String, Object> ();
		map.put("x", x);
		map.put("y", y);
		map.put("result", x+y);
		
		return map;
		
	}
	
	//@RequestBody - json으로 받아라
	@PostMapping("/add4")
	public HashMap<String, Object> add4(
			@RequestBody HashMap<String, Object> map)

	{
		//HttpRequestServlet 객체에 담아온다.
		//int x = Integer.parseInt(request.getParameter("x"));
		HashMap<String, Object> resultMap = new HashMap<String, Object> ();
		
		int x = Integer.parseInt(map.get("x").toString());
		int y = Integer.parseInt(map.get("y").toString());
		
		resultMap.put("x", x);
		resultMap.put("y", y);
		resultMap.put("result", x+y);
		
		return resultMap;
		
	}
	
	@PostMapping("/add5")
	public HashMap<String, Object> add5(
			@RequestBody HashMap<String, Object> map)

	{
		//HttpRequestServlet 객체에 담아온다.
		//int x = Integer.parseInt(request.getParameter("x"));
		HashMap<String, Object> resultMap = new HashMap<String, Object> ();
		
		Object x = map.get("x").toString();
		int y = Integer.parseInt(map.get("y").toString());
		int z = Integer.parseInt(map.get("z").toString());

		resultMap.put("x", x);
		resultMap.put("y", y);
		resultMap.put("z", z);

		resultMap.put("result", y*z);
		
		return resultMap;
		
	}
	
	@PostMapping("/getPayment")
	HashMap<String, Object> getPayment(
			@RequestBody HashMap<String, String> param)
	{
		int work_time = Integer.parseInt(param.get("work_time"));
		int per_pay = Integer.parseInt(param.get("per_pay"));
		String name = param.get("name");
		
		int pay = work_time*per_pay;
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("result", "OK");
		resultMap.put("msg", String.format("%s님의 주급은 %d입니다.", name, pay));
		
		return resultMap;
	}
}



	


