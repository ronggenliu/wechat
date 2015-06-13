package org.wechat.common.annotation;

import org.wechat.common.annotation.APIType.Type;
import org.wechat.common.annotation.Param.ParamType;
import org.wechat.common.annotation.SessionType.SType;

public class TestAnno {

	@Author("jasonXie")
	@APIType(Type.CONTROLLER)
	@Params({@Param(field="name",required=true),
			@Param(field="age",required=true,type=ParamType.POSITIVE_INT)
	})
	public void test1(String name,int age){
		
	}
	
	@Author({"桃子","成哥"})
	@APIType(Type.SERVICE)
	@Params({@Param(field="name",required=true),
		@Param(field="age",required=true,type=ParamType.POSITIVE_INT)
	})
	@SessionType(desc="ctest", value = SType.COMPELLED)
	@ReturnData({
			""
	})
	public String test2(String name,int age){
		
		return null;
	}
}
