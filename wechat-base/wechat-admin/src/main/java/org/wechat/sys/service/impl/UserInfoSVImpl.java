package	org.wechat.sys.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wechat.sys.mapper.UserInfoMapper;
import org.wechat.sys.model.UserInfo;
import org.wechat.sys.service.IUserInfoSV;


@Service("userInfoSV")
public class UserInfoSVImpl extends BaseSVImpl<UserInfo> implements IUserInfoSV {

//	private static Logger log = LoggerFactory.getLogger(UserInfoSVImpl.class);  
	
	@Autowired
	UserInfoMapper userInfoMapper;
	
	


}

