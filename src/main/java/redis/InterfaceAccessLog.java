package redis;

import java.util.Date;


/**
 * Created by Administrator on 2017/7/17.
 */
public class InterfaceAccessLog{
    private Integer id;
    private Integer interfaceId;
    private String interfaceName;
    private String callerCode;
    private String callerName;
    private String callerAuthKey;
    private String ip;
    private String url;
    private String params;
    private String requestMethod;
    private String requestHeader;
    private String requestBody;
    private String responseHeader;
    private String responseBody;
    private String status;
    private String statusCode;
    private String errorMsg;
    private String errorCode;
    private String errorType;
    private Date requestTime;
    private Date responseTime;
    private Date authBeginTime;
    private Date authEndTime;
    private Date toBeginTime;
    private Date toEndTime;
    private Date interfaceTime;
    private Float sumAccessTime;
    private Float sumAuthTime;
    private Float sumInterfaceTime;
    private Float sumInsideTime;
    private Float sumLogTime;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(Integer interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getCallerCode() {
        return callerCode;
    }

    public void setCallerCode(String callerCode) {
        this.callerCode = callerCode;
    }

    public String getCallerName() {
        return callerName;
    }

    public void setCallerName(String callerName) {
        this.callerName = callerName;
    }

    public String getCallerAuthKey() {
        return callerAuthKey;
    }

    public void setCallerAuthKey(String callerAuthKey) {
        this.callerAuthKey = callerAuthKey;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(String requestHeader) {
        this.requestHeader = requestHeader;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(String responseHeader) {
        this.responseHeader = responseHeader;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Date getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }

    public Date getAuthBeginTime() {
        return authBeginTime;
    }

    public void setAuthBeginTime(Date authBeginTime) {
        this.authBeginTime = authBeginTime;
    }

    public Date getAuthEndTime() {
        return authEndTime;
    }

    public void setAuthEndTime(Date authEndTime) {
        this.authEndTime = authEndTime;
    }

    public Date getToBeginTime() {
        return toBeginTime;
    }

    public void setToBeginTime(Date toBeginTime) {
        this.toBeginTime = toBeginTime;
    }

    public Date getToEndTime() {
        return toEndTime;
    }

    public void setToEndTime(Date toEndTime) {
        this.toEndTime = toEndTime;
    }

    public Date getInterfaceTime() {
        return interfaceTime;
    }

    public void setInterfaceTime(Date interfaceTime) {
        this.interfaceTime = interfaceTime;
    }

    public Float getSumAccessTime() {
        return sumAccessTime;
    }

    public void setSumAccessTime(Float sumAccessTime) {
        this.sumAccessTime = sumAccessTime;
    }

    public Float getSumAuthTime() {
        return sumAuthTime;
    }

    public void setSumAuthTime(Float sumAuthTime) {
        this.sumAuthTime = sumAuthTime;
    }

    public Float getSumInterfaceTime() {
        return sumInterfaceTime;
    }

    public void setSumInterfaceTime(Float sumInterfaceTime) {
        this.sumInterfaceTime = sumInterfaceTime;
    }

    public Float getSumInsideTime() {
        return sumInsideTime;
    }

    public void setSumInsideTime(Float sumInsideTime) {
        this.sumInsideTime = sumInsideTime;
    }

    public Float getSumLogTime() {
        return sumLogTime;
    }

    public void setSumLogTime(Float sumLogTime) {
        this.sumLogTime = sumLogTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
