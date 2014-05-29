import edu.mbl.cdp.macroprocessing.MyVariables;
import ij.IJ;
import ij.ImagePlus;
import ij.macro.ExtensionDescriptor;
import ij.macro.Functions;
import ij.macro.MacroExtension;
import ij.plugin.PlugIn;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import mmcorej.StrVector;
import org.json.JSONException;
import org.micromanager.MMStudioMainFrame;

 /*
 * Copyright © 2009 – 2013, Marine Biological Laboratory
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, 
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, 
 * this list of conditions and the following disclaimer in the documentation 
 * and/or other materials provided with the distribution.

 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS “AS IS” AND 
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
 * IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, 
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, 
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * The views and conclusions contained in the software and documentation are those of 
 * the authors and should not be interpreted as representing official policies, 
 * either expressed or implied, of any organization.
 */



/**
 *
 * @author Amitabh
 */
public class MacroExtensionsStub implements PlugIn, MacroExtension {

// http://stackoverflow.com/questions/15592769/rosin-thresholding-unimodal-thresholding-java    
// If you want to use parallel processing. Change the namespace for:
// import Catalano.Imaging.Concurrent.Filters;
        
    
    
                    
    @Override
    public void run(String arg) {       
        if (!IJ.macroRunning()) {
            IJ.error("Cannot install extensions from outside a macro!");
            return;
        }
        
        Functions.registerExtensions(this);
    }
    
    private ExtensionDescriptor[] extensions = {
                
        ExtensionDescriptor.newDescriptor("getGroupPresetName", this, ARG_STRING, ARG_OUTPUT+ARG_STRING),
        
        ExtensionDescriptor.newDescriptor("getChMetadata", this, ARG_NUMBER, ARG_STRING, ARG_OUTPUT+ARG_STRING),
        
        ExtensionDescriptor.newDescriptor("getSummaryMetadata", this, ARG_STRING, ARG_OUTPUT+ARG_STRING),
        
        ExtensionDescriptor.newDescriptor("setString", this, ARG_STRING, ARG_STRING),
        ExtensionDescriptor.newDescriptor("getString", this, ARG_STRING, ARG_OUTPUT+ARG_STRING),
        
        ExtensionDescriptor.newDescriptor("setDouble", this, ARG_STRING, ARG_NUMBER),
        ExtensionDescriptor.newDescriptor("getDouble", this, ARG_STRING, ARG_OUTPUT+ARG_NUMBER),
        
        ExtensionDescriptor.newDescriptor("setDoubleArray", this, ARG_NUMBER, ARG_ARRAY),
        ExtensionDescriptor.newDescriptor("getDoubleArray", this, ARG_NUMBER, ARG_OUTPUT+ARG_ARRAY),
    };

    @Override
    public ExtensionDescriptor[] getExtensionFunctions() {
        return extensions;
    }

    @Override
    public String handleExtension(String name, Object[] args) {

        if (name.equals("getChMetadata")) {
            int ch = ((Double) args[0]).intValue();
            String key = (String) args[1];
            String str = (String) getChMetadata(ch, key);
            //MyVariables.varStr.put(key, str);
            ((String[]) args[2])[0] = str;
            
        } else if (name.equals("getSummaryMetadata")) {
            String key = (String) args[0];
            String str = (String) getSummaryMetadata(key);
            ((String[]) args[1])[0] = str;
            
        } else if (name.equals("getGroupPresetName")) {
            String groupName = (String) args[0];
            String presetName = "Undefined";
            try {
                StrVector Channels = MMStudioMainFrame.getInstance().getCore().getAvailableConfigs(groupName);
                if (Channels.size() > 0) {
                String config = MMStudioMainFrame.getInstance().getCore().getConfigGroupState(groupName).Serialize().toString();
                for (int i=0; i < Channels.size(); i++){
                    String ChannelName = Channels.get(i);
                    String config2 = MMStudioMainFrame.getInstance().getCore().getConfigData(groupName, ChannelName).Serialize().toString();
                    if (stringMatch(config,config2)) {
                        presetName = ChannelName; break;
                    }
                }
                }
            } catch (Exception ex) {
                Logger.getLogger(MacroExtensionsStub.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            ((String[]) args[1])[0] = (String) presetName;
            
        } else if (name.equals("setString")) {
            String key = ((String) args[0]);
            String str = (String) args[1];
            MyVariables.varStr.put(key, str);
            
        } else if (name.equals("setDouble")) {
            String key = ((String) args[0]);
            double dVal = ((Double) args[1]);
            MyVariables.varDouble.put(key, dVal);
            
        } else if (name.equals("setDoubleArray")) {
            int iVal = ((Double) args[0]).intValue();
            Object[] dVal = ((Object[]) args[1]);            
            MyVariables.varObjectArray.set(iVal, dVal);            
            
        } else if (name.equals("getString")) {            
            String key = ((String) args[0]);
            ((String[]) args[1])[0] = (String) MyVariables.varStr.get(key);
            
        } else if (name.equals("getDouble")) {            
            String key = ((String) args[0]);
            ((Double[]) args[1])[0] = (Double) MyVariables.varDouble.get(key);
            
        } else if (name.equals("getDoubleArray")) {            
            int iVal = ((Double) args[0]).intValue();
            
            Object[] dVal = MyVariables.varObjectArray.get(iVal);
            ((Object[]) args[2])[0] = dVal;            
        }

        return null;
    }

    public static void main(String args[]) {
        String test = "C:\\Users\\Amitabh\\Downloads\\SM_cell1-bottom.tif";

        ImagePlus imp = new ImagePlus(test);
        //imp.setTitle(imp.getTitle() + "-RosinApplied");
        //imp.show();
        
        BufferedImage bi = imp.getBufferedImage();
        ImagePlus imp3 = new ImagePlus("", bi);
        imp3.show();
        

    }
    
    public boolean stringMatch(String str1, String str2) {
                
        if (str1.length()!=str2.length()) {
            return false;
        }
        
        char[] chars1 = str1.toCharArray();
        Arrays.sort(chars1);
        String sorted1 = new String(chars1);
        
        char[] chars2 = str2.toCharArray();
        Arrays.sort(chars2);
        String sorted2 = new String(chars2);
        
        if (sorted1.equals(sorted2)) {
            return true;
        }
        
        return false;
    }
    
    public static String getChMetadata(int ch, String key) {
        
        if (MyVariables.ChannelMetadata_ !=null && MyVariables.ChannelMetadata_.length >= ch) {
            if (MyVariables.ChannelMetadata_[ch] != null && MyVariables.ChannelMetadata_[ch].has(key)) {
                try {
                    return MyVariables.ChannelMetadata_[ch].getString(key);
                } catch (JSONException ex) {
                    Logger.getLogger(MacroExtensionsStub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return "Not found";
    }
    
    public static String getSummaryMetadata(String key) {
        
        if (MyVariables.SummaryMetadata !=null) {
            if (MyVariables.SummaryMetadata.has(key)) {
                try {
                    return MyVariables.SummaryMetadata.getString(key);
                } catch (JSONException ex) {
                    Logger.getLogger(MacroExtensionsStub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return "Not found";
    }
    
}
