import java.net.UnknownHostException;
import java.util.concurrent.Executors;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.common.JISystem;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.common.NotConnectedException;
import org.openscada.opc.lib.da.AccessBase;
import org.openscada.opc.lib.da.AddFailedException;
import org.openscada.opc.lib.da.Async20Access;
import org.openscada.opc.lib.da.AutoReconnectController;
import org.openscada.opc.lib.da.DuplicateGroupException;
import org.openscada.opc.lib.da.Server;


public class SCADAThread implements Runnable{
	
	private int num;
	
	private Server s;

	@Override
	public void run() {
		AutoReconnectController autos = null;
		  try {
		   JISystem.setAutoRegisteration(true);
		    
		   ConnectionInformation ci = new ConnectionInformation();
		   ci.setHost("172.16.120.130");
		   ci.setDomain("");
		   ci.setClsid("F8582CF2-88FB-11D0-B850-00C0F0104305");
		   ci.setUser("Administrator");
		   ci.setPassword("123456");
		    
		    
		    Server s = new Server(ci,Executors.newSingleThreadScheduledExecutor());
		         autos = new AutoReconnectController(s);
		   autos.connect();Thread.sleep(100);
		    
		    
		//   dumpTree(s.getTreeBrowser().browse(),0);
		    
//		   Group group = s.addGroup("Group0");
//		            group.setActive(true);
//		            final Item item = group.addItem("Random.Int4");
//		            item.setActive(true);
//		            Thread.sleep(100);
//		            System.out.println("读取值："+item.read(false).getValue());
//		            JIVariant value = JIVariant.makeVariant(new Integer(777));
//		   item.write(value);
		    
		    
		   final AccessBase access = new Async20Access(s,100,false);
		   System.out.println("===========Random.Int"+num);
		            access.addItem ( "Random.Int"+num, new DataCallbackDumper());
		            access.addItem ( "Random.Boolean", new DataCallbackDumper());
		            access.bind ();
		            Thread.sleep(1000*100*1000000);
		            access.unbind();
		            
		 
		  } catch (IllegalArgumentException e) {
		   e.printStackTrace();
		  } catch (UnknownHostException e) {   
		   e.printStackTrace();
		  } catch (JIException e) {  
		   e.printStackTrace();
		  } catch (NotConnectedException e) {  
		   e.printStackTrace();
		  } catch (DuplicateGroupException e) {             
		   e.printStackTrace();
		  } catch (AddFailedException e) {
		   e.printStackTrace();
		  } catch (InterruptedException e) {
		   e.printStackTrace();
		  }finally{
		   autos.disconnect();
		  }  
		
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Server getS() {
		return s;
	}

	public void setS(Server s) {
		this.s = s;
	}
	
	

}
