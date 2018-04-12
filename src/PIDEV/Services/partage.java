/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author dahem
 */
public class partage {
     public void partager(String nom,String description , String dateD,String Datef,String image){
    

        
          String domain="https://localhost/";
          //domain="https://google.fr/";
         String appId="157180834952275";
         String appSecret="62c913bb53d4ac66071484006a293386";
         String authURL="https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId+"&redirect_uri="+domain
                 +"&scope=ads_management,publish_actions";
         
         System.setProperty("webdriver.chrome.driver", "api/chromedriver_win32/chromedriver.exe");
         WebDriver driver= new ChromeDriver();
         driver.get(authURL);
         String accessToken="EAAC3d8olPEABAO89ZA9LquPx53LhMVfQw3njGdQrAS4YOBnbYZBEQXZBQ1CfZAbtjMZBIPtcOj91z85tHeWEWtWKifQdFXLZBKHZA13f5WkPyU4KbVffHf6gQlEz1XIpg2y5gpr88CVVJ9lWP1h9XTCcyJ5VMF0WM9LMfHOeawKOdhXckBQIWGctDJ1hDrK1FRrJHoToEOLZAgZDZD" ;
         
         boolean ok=true;
         while(ok)
         {
//             if ( (! driver.getCurrentUrl().contains("facebook.com")) && (driver.getCurrentUrl()!=authURL) )
//             {
//                 String url =driver.getCurrentUrl();
//             //    accessToken =url.replaceAll(".*#access_token=(.+)&.*", "$1");
//                 System.out.println(accessToken);
//                
//                 ok=false;
//              }
             
         }
         
         System.out.println("act:"+accessToken);
         driver.quit();
         FacebookClient fbClient = new DefaultFacebookClient(accessToken);
              User me = fbClient.fetchObject("me", User.class);
              System.out.println(me.getUsername());
              FacebookType publishPhotoResponse = fbClient.publish("me/photos", FacebookType.class,
  BinaryAttachment.with(image, getClass().getResourceAsStream("/imageEvenement/"+image)),
  Parameter.with("message", "Evenement"+" "+ nom+" "+"a"+" "+description+" "+"Du"+" "+dateD+" "+"jusqua "+" "+Datef));
//            
//              FacebookType publishMessageResponse =
//            fbClient.publish("me/feed", FacebookType.class,
//                    com.restfb.Parameter.with("message","Evenement"));
//          
//
//              System.out.println("Published message ID: " + publishMessageResponse.getId());

    /**
     *
     */
    
        
    
            
    }
}
