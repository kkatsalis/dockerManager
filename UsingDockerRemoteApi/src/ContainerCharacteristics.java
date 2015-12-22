import org.json.JSONObject;
import org.json.JSONException;
import java.util.Hashtable;

public class ContainerCharacteristics {
	
	String Hostname;
    String Domainname;
    String User;
    boolean AttachStdin; 
    boolean AttachStdout;
    boolean AttachStderr;
    boolean Tty;
    boolean OpenStdin;
    boolean StdinOnce;
    String Env;
    String Cmd;
    String Entrypoint;
    String Image;
    Hashtable Labels;
    String Mounts;
    String WorkingDir;
    boolean NetworkDisabled;
    String MacAddress;
    Hashtable ExposedPorts;
    String StopSignal;
    Hashtable HostConfig = null;
    
    
    public ContainerCharacteristics(){
    	
    	Hostname = "";
    	Domainname = "";
    	User = "";
    	AttachStdin = false;
    	AttachStdout = true;
    	AttachStderr = true;
    	Tty = false;	
    	OpenStdin = false;
    	StdinOnce = false;
    	Env = null;
    	Cmd = null;
    	Entrypoint = null;
    	Image = "testposting";
    	Labels = new Hashtable();
    	//Mounts = "[]";
    	WorkingDir ="";
    	NetworkDisabled = false;
    	MacAddress =  "";
    	ExposedPorts = new Hashtable();
    	StopSignal = "SIGTERM";
    	HostConfig =  new Hashtable();
    	
    
    }
    
	public String getHostname() {
	    return Hostname;
	}
	
	public void setHostname(String Hostname) {
	    this.Hostname = Hostname;
	}

	
	public String getDomainname() {
	    return Domainname;
	}
	
	public void setDomainname(String Domainname) {
	    this.Domainname = Domainname;
	}
	
	public String getUser() {
	    return User;
	}
	
	public void setUser(String User) {
	    this.User = User;
	}
	
	public boolean getAttachStdin() {
	    return AttachStdin;
	}
	
	public void setAttachStdin(boolean AttachStdin) {
	    this.AttachStdin = AttachStdin;
	}
	
	
	public boolean getAttachStdout() {
	    return AttachStdout;
	}
	
	public void setAttachStdout(boolean AttachStdout) {
	    this.AttachStdout = AttachStdout;
	}

	public boolean getAttachStderr() {
	    return AttachStderr;
	}
	
	public void setAttachStderr(boolean AttachStderr) {
	    this.AttachStderr = AttachStderr;
	}
	
	
	public boolean getTty() {
	    return Tty;
	}
	
	public void setTty(boolean Tty) {
	    this.Tty = Tty;
	}
	
	
	public boolean getOpenStdin() {
	    return OpenStdin;
	}
	
	public void setOpenStdin(boolean OpenStdin) {
	    this.OpenStdin = OpenStdin;
	}
	
	public boolean getStdinOnce() {
	    return StdinOnce;
	}
	
	public void setStdinOnce(boolean StdinOnce) {
	    this.StdinOnce = StdinOnce;
	}
	
	public String getEnv() {
	    return Env;
	}
	
	public void setEnv(String Env) {
	    this.Env = Env;
	}
	
	public String getCmd() {
	    return Cmd;
	}
	
	public void setCmd(String Cmd) {
	    this.Cmd = Cmd;
	}
	
	
	public String getEntrypoint() {
	    return Entrypoint;
	}
	
	public void setEntrypoint(String Entrypoint) {
	    this.Entrypoint = Entrypoint;
	}
	
	public String getImage() {
	    return Image;
	}
	
	public void setImage(String Image) {
	    this.Image = Image;
	}
	
	/*	
	public JSONObject getLabels() {
	    return Labels;
	}
	
	public void setLabels(JSONObject Labels) {
	    this.Labels = Labels;
	}
	*/
	public String getMounts() {
	    return Mounts;
	}
	
	public void setMounts(String Mounts) {
	    this.Mounts = Mounts;
	}
	
	
	public String getWorkingDir() {
	    return WorkingDir;
	}
	
	public void setWorkingDir(String WorkingDir) {
	    this.WorkingDir = WorkingDir;
	}
	
	public void setNetworkDisabled(boolean NetworkDisabled) {
	    this.NetworkDisabled = NetworkDisabled;
	}
	
	public boolean getNetworkDisabled() {
	    return NetworkDisabled;
	}
	
	public String getMacAddress() {
	    return MacAddress;
	}
	
	public void setMacAddress(String MacAddress) {
	    this.MacAddress = MacAddress;
	}
/*
	public String getExposedPorts() {
	    return ExposedPorts;
	}
	
	public void setExposedPorts(String ExposedPorts) {
	    this.ExposedPorts = ExposedPorts;
	}
	
*/	
	public String setStopSignal() {
	    return StopSignal;
	}
	
	public void getStopSignal(String StopSignal) {
	    this.StopSignal = StopSignal;
	}
	/*
	public JSONObject getHostConfig() {
	    return HostConfig;
	}
	
	public void setHostConfig(JSONObject HostConfig) {
	    this.HostConfig = HostConfig;
	}
	 */
	
}