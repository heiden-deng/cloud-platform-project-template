package com.heiden.dbp.zuul.vo.response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

import com.heiden.dbp.zuul.utils.CommonDateUtils;
import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * 请求结果，用户发出请求后，给用户的反馈对象， 最终以json接口的形式，提供给client
 * 
 * @author heiden
 *
 */
public class RequestResult {

  private static final int STATUS_SUCCESS = 200;

  private static final int STATUS_FAILURE = 500;

  private int status = 200;// default value

  private String data; // 涉及到加密，需要先将data转换成json

  // private String internalErrorMsg; // 真实的错误信息

  private String message;

  private String timestamp;

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public String getResponseId() {
    return responseId;
  }

  public void setResponseId(String responseId) {
    this.responseId = responseId;
  }

  private String requestId;
  private String responseId;

  // private Map<String, List<String>> errorMsg;// 提示给用户的错误信息

  public void setStatus(int status) {
    this.status = status;
  }

  public RequestResult() {
    this.message = "";
    this.data = "";
    this.requestId = "";
    this.responseId = "";
    this.timestamp = CommonDateUtils.date2String(new Date(),
            CommonDateUtils.DatePattern.yyyyMMddHHmmssLine);
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public RequestResult(Object dataObj) {
    this.status = STATUS_SUCCESS;
    String json = toJson(dataObj);
    this.message = json;
    this.data = "";
    this.requestId = "";
    this.responseId = "";
    this.timestamp = CommonDateUtils.date2String(new Date(),
            CommonDateUtils.DatePattern.yyyyMMddHHmmssLine);
  }

  public int getStatus() {
    return status;
  }

  public String getData() {
    return data;
  }

  public void setDataObj(Object dataObj) {
    this.status = STATUS_SUCCESS;
    String json = toJson(dataObj);
    this.data = json;
  }

  public void setData(String data) {
    this.status = STATUS_SUCCESS;
    this.data = data;
  }

  /*
   * public Map<String, List<String>> getErrorMsg() { return errorMsg; }
   *
   * public void setErrorMsg(Map<String, List<String>> errorMsg) { this.status = STATUS_FAILURE;
   * this.errorMsg = errorMsg; }
   *
   * public String getInternalErrorMsg() { return internalErrorMsg; }
   *
   * public void setInternalErrorMsg(String internalErrorMsg) { this.internalErrorMsg =
   * internalErrorMsg; }
   */

  private final String toJson(Object obj) {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(outputStream);
      jsonGenerator.writeObject(obj);
      String json = new String(outputStream.toByteArray());
      return json;
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      IOUtils.closeQuietly(outputStream);
    }
  }

}
