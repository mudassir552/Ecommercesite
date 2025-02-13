/*//ading this to test the thing
 * package com.ecommerceproject.utils;
 * 
 * import org.springframework.beans.factory.annotation.Value; import
 * org.springframework.http.HttpEntity; import
 * org.springframework.http.HttpHeaders; import
 * org.springframework.http.HttpMethod; import
 * org.springframework.http.ResponseEntity;
 * 
 * import org.springframework.web.client.RestTemplate; import java.util.Map;
 * 
 * 
 * 
 * public class JenkinsBuild {
 * 
 * 
 * 
 * public JenkinsBuild() {
 * 
 * // TODO Auto-generated constructor stub }
 * 
 * 
 * private final RestTemplate restTemplate = new RestTemplate();
 * 
 * 
 * @Value("${jenkins.url}") private String jenkinsUrl;
 * 
 * @Value("${jenkins.username}") private String jenkinsUsername;
 * 
 * @Value("${jenkins.token}") private String jenkinsToken;
 * 
 * private String
 * jenkinsUrl="http://localhost:8080/job/Ecommerce/build?token=testtoken";
 * 
 * private String jenkinsUsername="root"; private String
 * jenkinsToken="testtoken";
 * 
 * public void triggerBuild() {
 * 
 * 
 * HttpHeaders headers = new HttpHeaders();
 * headers.setBasicAuth(jenkinsUsername, jenkinsToken);
 * 
 * HttpEntity<String> entity = new HttpEntity<>(headers); ResponseEntity<String>
 * response = restTemplate.exchange(jenkinsUrl, HttpMethod.POST, entity,
 * String.class);
 * 
 * if (response.getStatusCode().is2xxSuccessful()) {
 * System.out.println("Build triggered successfully!"); } else {
 * System.out.println("Failed to trigger build. Status code: " +
 * response.getStatusCode()); }
 * 
 * 
 * 
 * 
 * } }
 */
