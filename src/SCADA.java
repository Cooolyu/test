import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.common.JISystem;
import org.jinterop.dcom.core.JIVariant;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.common.NotConnectedException;
import org.openscada.opc.lib.da.AddFailedException;
import org.openscada.opc.lib.da.AutoReconnectController;
import org.openscada.opc.lib.da.DuplicateGroupException;
import org.openscada.opc.lib.da.Group;
import org.openscada.opc.lib.da.Item;
import org.openscada.opc.lib.da.Server;


public class SCADA {
	public static void main ( final String[] args ) throws Throwable
    {
  // create connection information
  final ConnectionInformation ci = new ConnectionInformation ();
  ci.setHost("127.0.0.1");
  ci.setDomain("");
  // ci.setProgId("Matrikon.OPC.Simulation.1");
//  ci.setProgId("OPC.SimaticNET"); //s7 注意 使用progId必须要求dcom配置正确
  ci.setClsid("F8582CF2-88FB-11D0-B850-00C0F0104305");//other
  //ci.setClsid("B6EACB30-42D5-11D0-9517-0020AFAA4B3C");// simatic S7
  ci.setUser("Administrator");
  ci.setPassword("123456");

  // create a new server
  final Server server = new Server ( ci, Executors.newSingleThreadScheduledExecutor () );
  try
  {
    server.connect ();

    Group group = server.addGroup ( "Group0" );
    // group is initially active ... just for demonstration
    group.setActive ( true );
  
    // Add a new item to the group
    final Item item = group.addItem ( "Random.Int4" );
  
    String[] test = new String[]{"Random.Int4"};
  
        
    Map<String,Item> map = new HashMap<String,Item>();
    map = group.addItems(test);
//  final AccessBase access = new Async20Access(s,100,false);
//  access.addItem ( "sim.test.D0", new DataCallbackDumper());
//  access.bind ();
//  Thread.sleep(100*1000);
//  access.unbind();

    
    System.out.println(map.keySet());
    while(true) {
    	for(String s : map.keySet()){
    	      Item it = map.get(s);
    	      System.out.println(s + " ===============  " + it.read(true).getValue());
    	    }
    	Thread.sleep(100);
    }
    
      
      
        // Items are initially active ... just for demonstration
//        item.setActive ( true );
//        item.write(new  JIVariant("121"));
//        
//        System.out.println(item.read(true).getValue() + "  <------==");
        
        // Add some more items ... including one that is already existing
       // final Map<String, Item> items = group.addItems ( "Saw-toothed Waves.Int2", "Saw-toothed Waves.Int4" );

      
  }
  catch ( final JIException e )
  {
      System.out.println ( String.format ( "%08X: %s", e.getErrorCode (), server.getErrorMessage ( e.getErrorCode () ) ) );
  }
    }
}