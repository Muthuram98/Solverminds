package commonMethods;

public interface Solverminds_Locators {
//LOGIN	

    public static String username="user>//input[@id='outlined-size-normal']";	
	public static String password="pass>//input[@id='outlined-adornment-password']";
	public static String login="login>//span[text()='Login']";
	public static String selectvessel="vessel>//input[@id='demo-simple-select-outlined']";
	public static String clickvessel="clickvessel>//*[@id='demo-simple-select-outlined-option-0']";
	public static String clickvessel2="clickvessel2>//*[@id='demo-simple-select-outlined-option-1']";
	
	
	public static String schedule="schedule>//img[@alt='04-SheduleVoyage']";
	
	public static String newschedule="newfile>(//*[@class='image_size '])[1]";
	public static String stow_zoomin = "stow_zoomin>//*[@id='6']/div/div/img[1]";
	public static String canvas6 = "canvas6>(//*[@class='MuiCardContent-root'])[6]";
	public static String searchservice = "search>(//*[@src='assets/images/common-icons/search.svg'])[1]";
	public static String toronto = "expressname>//*[text()='Toronto Express']";
	public static String voyagefrom = "voyagefrom>(//*[text()='Voyage From']/following::input)[1]";
	public static String voyageto = "to>(//*[text()='Voyage From']/following::input)[2]";

	public static String boundfrom = "boundfrom>//*[@class='MuiSelect-root MuiSelect-select MuiSelect-selectMenu MuiSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputMarginDense MuiOutlinedInput-inputMarginDense']";
	public static String selectboundS = "selectS>//*[@data-value='S']";
	public static String boundto = "boundto>(//*[@class='MuiSelect-root MuiSelect-select MuiSelect-selectMenu MuiSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputMarginDense MuiOutlinedInput-inputMarginDense'])[2]";
	public static String selectboundW = "selectW>//*[@data-value='W']";
	public static String Show = "show>//span[text()='Show']";
	public static String portsearch = "searchport>//*[text()=' Schedule Details ']/following::img]";
	public static String selectport = "selectport>//*[text()='SOGESTER']";
	public static String add = "add>//*[text()='Add']";
	public static String save = "save>//*[@src='assets/images/Mastertoolbar-icons/Save.svg']";
	public static String optimiser = "optimiser>//*[@alt='Optimizer']";
	public static String crane = "crane>//*[@src='/assets/images/optimizer-icons/active/icon-Optimizr-Cranes.svg']";
	public static String solvecrane = "solve>(//*[@class='MuiButtonBase-root MuiIconButton-root'])[3]";
	public static String semiauto = "auto>//span[text()='Semi Auto Slot']";

	public static String stow_table = "stow_table>//*[@id='4']";
	public static String dropdown = "drop>//*[@id='demo-simple-select-autowidth']";
	

	public static String OutsideCanvas="Full Canvas>//*[@id='AllBayCanvas']";
	public static String canvas_bay_1="Bay 01 canvas>(//*[@id='AllBayCanvas']//following::canvas)[1]";
	public static String TwinBayCanvas="TwinBayCanvas>//*[@id='TwinBayCanvas']//following::canvas";
	public static String stowNo="StowNumber>//div[contains(text(),'StowNo')]";
	public static String threeD_view="ThreeD view>//*[@alt='icon_3dview']";
	public static String view_360="360 view>//*[@data-tip='Turn off 3D Rotate']";
	public static String switchTo_2D_3D="Switch to 2D and 3D>//*[contains(@alt,'Switch to ')]";
	public static String bayBackward="Bay Back>(//*[text()='Logout']//following::div)[5]";
	public static String bayForward="Bay Front>(//*[text()='Logout']//following::div)[37]";
	public static String baySelect="Bay Select>(//*[text()='Logout']//following::div)[7]";//(//*[@class='MuiSvgIcon-root css-1a1893a'])[2]
	public static String containers_WW="Containers WW>//*[text()='WW']";//*[text()='All Bays']
	public static String containers_WW1="Containers WW>(//*[text()='WW'])[1]";
    public static String container_All="All container>//div[@class='css-1y9z8ki']";
	public static String port="Port>//*[@id='orientCubeWrapper']";
    
	public static String containerDetails="Container Details>//*[text()='Container No :']//following::p";
    public static String firstBay="First bay>(//*[@class='MuiSvgIcon-root css-1a1893a'])[2]//following::p[text()='01']";
    
    public static String ThreedPage_canvas="ThreeDPage Canvas>//*[@id='orientCubeWrapper']//preceding::canvas";
    public static String Add_cargo_close = "Add_cargo_close>//*[text()='X']";
	
	
}
