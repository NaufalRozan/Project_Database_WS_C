/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.ws.c.mantan;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rozan
 */
@RestController
@CrossOrigin
public class myController {
    C2020ws data = new C2020ws();
    C2020wsJpaController dctrl = new C2020wsJpaController();
    
    @RequestMapping("/getNama")
    @ResponseBody
    public String getNamebyID(){
        
        try {data = dctrl.findC2020ws(1);}
        catch (Exception error){}
        
        return data.getName();
    }
    
    @RequestMapping("/getLahir")
    @ResponseBody
    public String getDatebyID (){
        
        try{data = dctrl.findC2020ws(1);}
        catch(Exception error) {}
        
        return data.getBirthdate().toString();
    }
    
    @RequestMapping("/GET")
    @ResponseBody
    public List<C2020ws> getDatabyID(){
        List<C2020ws> datas = new ArrayList<>();
        try {datas = dctrl.findC2020wsEntities();}
        catch(Exception error) {}        
        return datas;
    }
    
    @RequestMapping("/getData/{id}")
    @ResponseBody
    public String getData (@PathVariable("id") int id){
        try {data = dctrl.findC2020ws(id);}
        catch(Exception error) {}
        
        String result = data.getName()+ "<br>" + data.getBirthdate().toString(); 
        return result;
    }
    
    @RequestMapping(value ="/POST", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public String sendData(HttpEntity<String> kiriman) throws Exception{
        String message="no action";
        String json_receive = kiriman.getBody();
        ObjectMapper mapper = new ObjectMapper();
        C2020ws data = new C2020ws(); //jika ingin banyak data pake List atau ArrayList
        data = mapper.readValue(json_receive, C2020ws.class);
        dctrl.create(data);
        message = data.getName()+" Saved";
        return message;
    }
    

    @RequestMapping(value ="/PUT", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public String editData(HttpEntity<String> kiriman) throws Exception{
        String message="no action";
        String json_receive = kiriman.getBody();
        ObjectMapper mapper = new ObjectMapper();
        C2020ws newdatas = new C2020ws(); //jika ingin banyak data pake List atau ArrayList
        
        newdatas = mapper.readValue(json_receive, C2020ws.class);
        try {dctrl.edit(newdatas);} catch (Exception e){}
        message = newdatas.getName()+" Saved";
        return message;
    }
    
        @RequestMapping(value ="/DELETE", method = RequestMethod.DELETE, consumes = APPLICATION_JSON_VALUE)
    public String deleteData(HttpEntity<String> kiriman) throws Exception{
        String message="no action";
        String json_receive = kiriman.getBody();
        ObjectMapper mapper = new ObjectMapper();
        C2020ws newdatas = new C2020ws(); //jika ingin banyak data pake List atau ArrayList     
        newdatas = mapper.readValue(json_receive, C2020ws.class);
        dctrl.destroy(newdatas.getId());
        return "deleted";
    }
    
}
