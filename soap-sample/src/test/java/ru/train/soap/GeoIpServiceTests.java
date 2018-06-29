package ru.train.soap;

import com.cdyne.ws.IP2Geo;
import com.cdyne.ws.IPInformation;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void geoTest(){
    IPInformation ipInformation = new IP2Geo().getIP2GeoSoap().resolveIP("178.140.243.66", "");
    System.out.println(ipInformation.getCountry());
    System.out.println(ipInformation.getCity());
    System.out.println(ipInformation.getRegionName());
    assertEquals(ipInformation.getCountry(),"Russian Federation");
  }

  @Test
  public void geoTestNotFound(){
    IPInformation ipInformation = new IP2Geo().getIP2GeoSoap().resolveIP("178.140.243.QQ", "");
    System.out.println(ipInformation.getCountry());
    assertEquals(ipInformation.getOrganization(),"Not Found");
  }
}
