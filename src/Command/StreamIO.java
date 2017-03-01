package Command;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.gson.TypeAdapter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
public class StreamIO{
  public static String read(InputStream inStream){
    String jsonString = "";
    try{
      final InputStreamReader r = new InputStreamReader(inStream/*, "UTF-8"*/);
      final StringBuilder strAccum = new StringBuilder();
      final char[] buf = new char[1024];
      int len;
      while((len = r.read(buf)) > 0)
        strAccum.append(buf, 0, len);
      r.close();
      jsonString = strAccum.toString();}
    catch(Exception e){}
    return jsonString;}
  
  
  public static void write(OutputStream outStream, String jsonString){
    try{
      final OutputStreamWriter w = new OutputStreamWriter(outStream/*, "UTF-8"*/);
      w.write(jsonString);
      w.flush();
      w.close();}
    catch(Exception e){}}
  
  
  private static String base64encode(Object cmd){
    final ByteArrayOutputStream bo = new ByteArrayOutputStream();
    try{
       final ObjectOutputStream so = new ObjectOutputStream(bo);
       so.writeObject(cmd);
       so.flush();}
    catch(IOException e){}
    return Base64.getEncoder().encodeToString(bo.toByteArray());}
  
  
  private static ICommand base64decode(String s){
    try{
      return (ICommand)new ObjectInputStream(new ByteArrayInputStream(
        Base64.getDecoder().decode(s))).readObject();}
    catch(Exception e){
      return null;}}
  
  
  
  public static Gson getCommandContainerRWer(){
    return new GsonBuilder().registerTypeAdapter(CommandContainer.class,
      new TypeAdapter<CommandContainer>(){
      public CommandContainer read(JsonReader r) throws IOException{
        r.beginObject();
        r.nextName();
        final int listLen = r.nextInt();
        r.nextName();
        r.beginArray();
        final List<String> str_type = new ArrayList<String>();
        for(int i = 0; i < listLen; i++){ //The type
        	str_type.add(r.nextString());System.out.println("<<"+str_type.get(0)+">>");}
        r.endArray();
        r.nextName();
        r.beginArray();
//        final List<Object> icommand = new ArrayList<Object>();
        final List<ICommand> icommand = new ArrayList<ICommand>();
        for(int i = 0; i < listLen; i++) { //The icommand
        	icommand.add(base64decode(r.nextString())); System.out.println("<<" + icommand.get(0) + ">>"); } //NULL WHYYY
        r.endArray();
        r.endObject();
        return new CommandContainer(str_type, icommand);}
      
      public void write(JsonWriter w, CommandContainer cc) throws IOException{
        w.beginObject();
        w.name("listLen").value(cc.str_type.size());
        w.name("str_type");
        w.beginArray();
        for(String s : cc.str_type){
        	w.value(s);}
        w.endArray();
        w.name("icommand");
        w.beginArray();
        for(Object cmd : cc.icommand)
        	w.value(base64encode(cmd));
        w.endArray();
        w.endObject();}}).create();}}