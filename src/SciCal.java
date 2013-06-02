import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class SciCal extends MIDlet implements CommandListener {

	private Display display;
	protected boolean started;
	
	int cgIndex;
	private Command exitCommand;
	private Command showCommand;
	private Command backCommand;
	private Command helpCommand;
	private Command calCommand;	
	private Alert alHelp;
    private String strHelp;	
	public TextField txOper1,txOper2,txOper3,txOper4;
	public Form fmMain,fmSub;
	private ChoiceGroup cgOper;
	private ChoiceGroup cgBase;	
	Ticker tcName;
    String[] stringArray = 
    {
    	"Modulas & Exponential", 
    	"Factorial & Sum of Digits",
    	"Dec-Bin-Oct-Hex"
	};
    String[] stringBase = 
    {
    	"Decimal", 
    	"Binary",
    	"Octal",    	
    	"Hex"
	};
	
	protected void startApp() {
		if(!started) 
		{
			display = Display.getDisplay(this);
			display.setCurrent(new SplashScreen(this));
		}
	}

	protected void pauseApp( ) {
	}

	protected void destroyApp(boolean unconditional) {
	}

	void splashScreenDone()
	{
		fmMain = new Form("SciCal Ver 1.0");
		exitCommand = new Command("Exit", Command.EXIT, 0);
		showCommand = new Command("Show", Command.SCREEN, 0);
		helpCommand = new Command("Help", Command.SCREEN, 0);
		fmMain.addCommand(exitCommand);
		fmMain.addCommand(showCommand);
		fmMain.addCommand(helpCommand);		
		cgOper=new ChoiceGroup("Select operation:", ChoiceGroup.EXCLUSIVE, stringArray,null);
		fmMain.append(cgOper);
		tcName=new Ticker("© 2006-2007 Salil Walavalkar");
		fmMain.setTicker(tcName);
		fmMain.setCommandListener(this);
		display.setCurrent(fmMain);
		started = true;
        strHelp="This midlet provides functions such as: "
        +"modulas,exponential,factorial,base conversion etc.. \n"
        +"Developer: Salil Walavalkar \n"
        +"Email: salil911@yahoo.com \n";
	}
	
	boolean IsNotEmpty(TextField txTmp)
	{
		if(txTmp.getString().length()==0)
			return false;
		else
			return true;
	}

	void fnModExpoLoad()
	{
		fmSub = new Form("Modulas & Exponential");
		backCommand = new Command("Back", Command.BACK, 0);
		calCommand = new Command("Calulate", Command.SCREEN, 0);		
		txOper1=new TextField("Oper 1","",5,TextField.NUMERIC);
		txOper2=new TextField("Oper 2","",5,TextField.NUMERIC);
		txOper3=new TextField("Modulas","",20,TextField.UNEDITABLE);
		txOper4=new TextField("Exponential","",20,TextField.UNEDITABLE);
		fmSub.append(txOper1);
		fmSub.append(txOper2);
		fmSub.append(txOper3);
		fmSub.append(txOper4);
	}

	void fnModExpoCalc()
	{
		long i=1,c=1,a,b;
		a=Long.parseLong(txOper1.getString());
		b=Long.parseLong(txOper2.getString());
		if(b==0)
			txOper3.setString("Cannot Divide By 0");
		else
		{
			while(i<=b)
			{
				c=c*a;
				i++;
			}	
			txOper3.setString(String.valueOf(a%b));
		}
		txOper4.setString(String.valueOf(c));
	}
	
	void fnFactSumLoad()
	{
		fmSub = new Form("Factorial & Sum Of Digits");
		backCommand = new Command("Back", Command.BACK, 0);
		calCommand = new Command("Calulate", Command.SCREEN, 0);				
		txOper1=new TextField("Enter number:","",5,TextField.NUMERIC);
		txOper2=new TextField("Factorial:","",20,TextField.UNEDITABLE);
		txOper3=new TextField("Sum of digits:","",20,TextField.UNEDITABLE);
		fmSub.append(txOper1);
		fmSub.append(txOper2);
		fmSub.append(txOper3);
	}
	
	void fnFactSumCalc()
	{
		// Factorial
		long a,i,f=1;
		a=Long.parseLong(txOper1.getString());
		for (i=a;i>1;i--)
		{
			f=f*i;
		}
		txOper2.setString(String.valueOf(f));

		// Sum of Digits
		f=0;
		while (a>0)
		{
			i=a%10;
			f=f+i;
			a=a/10;
		}
		txOper3.setString(String.valueOf(f));		
	}

	void fnDec2Bin2Oct2HexLoad()
	{
		fmSub = new Form("Dec-Bin-Oct-Hex");
		backCommand = new Command("Back", Command.BACK, 0);
		calCommand = new Command("Calulate", Command.SCREEN, 0);				
		cgBase=new ChoiceGroup("Calculate for: ", ChoiceGroup.POPUP, stringBase,null);
		fmSub.append(cgBase);
		txOper1=new TextField("Decimal: ","",20,TextField.NUMERIC);
		txOper2=new TextField("Binary: ","",20,TextField.NUMERIC);
		txOper3=new TextField("Oct:","",20,TextField.NUMERIC);
		txOper4=new TextField("Hex:","",20,TextField.ANY);
		fmSub.append(txOper1);
		fmSub.append(txOper2);
		fmSub.append(txOper3);
		fmSub.append(txOper4);		
	}

	void fnDec2Bin2Oct2HexCalc()
	{
		int iOper1;

		try
		{
			if(cgBase.getSelectedIndex()==0) //Decimal
			{
				iOper1=Integer.parseInt(txOper1.getString());
				txOper2.setString(Integer.toBinaryString(iOper1));
				txOper3.setString(Integer.toOctalString(iOper1));
				txOper4.setString(Integer.toHexString(iOper1));
			}
			else if (cgBase.getSelectedIndex()==1) //Binary
			{
				iOper1=Integer.parseInt(txOper2.getString(),2);
				txOper1.setString(String.valueOf(iOper1));
				txOper3.setString(String.valueOf(Integer.toOctalString(iOper1)));
				txOper4.setString(String.valueOf(Integer.toHexString(iOper1)));
			}
			else if (cgBase.getSelectedIndex()==2) //Octal
			{
				iOper1=Integer.parseInt(txOper3.getString(),8);
				txOper1.setString(String.valueOf(iOper1));
				txOper2.setString(String.valueOf(Integer.toBinaryString(iOper1)));
				txOper4.setString(String.valueOf(Integer.toHexString(iOper1)));
			}
			else if (cgBase.getSelectedIndex()==3) //Hex
			{
				iOper1=Integer.parseInt(txOper4.getString(),16);
				txOper1.setString(String.valueOf(iOper1));
				txOper2.setString(String.valueOf(Integer.toBinaryString(iOper1)));
				txOper3.setString(String.valueOf(Integer.toOctalString(iOper1)));
			}
		}
		catch(Exception e)
		{
			txOper1.setString("");
			txOper2.setString("");
			txOper3.setString("");
			txOper4.setString("");
		}
	}

	public void commandAction(Command c, Displayable d) {
		if (c == exitCommand) 
		{
			destroyApp(true);
			notifyDestroyed();
		}
		else if (c == showCommand) 
		{
			cgIndex = cgOper.getSelectedIndex();
			if(cgIndex==0)
				fnModExpoLoad();
			else if(cgIndex==1)
				fnFactSumLoad();
			else if(cgIndex==2)
				fnDec2Bin2Oct2HexLoad();

			//Common Code
			fmSub.addCommand(backCommand);
			fmSub.addCommand(calCommand);
			fmSub.setCommandListener(this);
			fmSub.setTicker(tcName);				
			display.setCurrent(fmSub);		
		}
		else if (c == helpCommand) 
		{
 	      	alHelp=new Alert("Help",strHelp,null,null);        	
        	alHelp.setTimeout(Alert.FOREVER);
        	alHelp.setTicker(tcName);
        	display.setCurrent(alHelp);      
		}
		else if (c == backCommand) 
		{
			display.setCurrent(fmMain);
		}
		else if (c == calCommand) 
		{
			cgIndex = cgOper.getSelectedIndex();
			if(cgIndex==0 && IsNotEmpty(txOper1) && IsNotEmpty(txOper2))
				fnModExpoCalc();
			else if(cgIndex==1 && IsNotEmpty(txOper1))
				fnFactSumCalc();
			else if(cgIndex==2)
				fnDec2Bin2Oct2HexCalc();
		}
	}
	
   // method needed by lots of classes, shared by putting it here
    static Image createImage(String filename)
    {
        Image image = null;
        try
        {
            image = Image.createImage(filename);
        }
        catch (java.io.IOException ex)
        {
            // just let return value be null
        }
        return image;
    }
}