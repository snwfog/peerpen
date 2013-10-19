package com.peerpen.model;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class PeerTest
{
  @Test
  public void createPeerTest()
  {
    String firstName = "Charles";
    String lastName = "Charles";
    String email = "donchoa@gmail.com";
    String userName = "snwfog";
    String password = "mypassword123";
    int point = 0;
    int rankId = 0;
    String personalWebsite = "http://charlescy.com";

    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("first_name", firstName);
    map.put("last_name", lastName);
    map.put("email", email);
    map.put("username", userName);
    map.put("password", password);

    Peer p = new Peer(map);

    System.out.println(p.toString());

  }

}
