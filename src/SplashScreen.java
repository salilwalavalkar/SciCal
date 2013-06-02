// Splash Screen

import javax.microedition.lcdui.*;

class SplashScreen
    extends Canvas
    implements Runnable
{
    private final SciCal midlet;
    private Image splashImage;
    private volatile boolean dismissed = false;


    SplashScreen(SciCal midlet)
    {
        this.midlet = midlet;
        setFullScreenMode(true);
        splashImage = SciCal.createImage("/splash.png");
        new Thread(this).start();
    }


    public void run()
    {
        synchronized(this)
        {
            try
            {
                wait(3000L);   // 3 seconds
            }
            catch (InterruptedException e)
            {
                // can't happen in MIDP: no Thread.interrupt method
            }
            dismiss();
        }
    }


    public void paint(Graphics g)
    {
        int width = getWidth();
        int height = getHeight();
        g.setColor(0x00FFFFFF);  // white
        g.fillRect(0, 0, width, height);

        if (splashImage != null)
        {
            g.drawImage(splashImage,
                        width/2,
                        height/2,
                        Graphics.VCENTER | Graphics.HCENTER);
            splashImage = null;
        }
    }


    public synchronized void keyPressed(int keyCode)
    {
        dismiss();
    }


    private void dismiss()
    {
        if (!dismissed)
        {
            dismissed = true;
            midlet.splashScreenDone();
        }
    }
}
