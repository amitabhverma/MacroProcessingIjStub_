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
package edu.mbl.cdp.macroprocessing;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONObject;

/**
 *
 * @author Amitabh
 */
public class MyVariables {
    ///////////////////////////////////////////
    // OPS EXT
    public static PropertyChangeSupport OPSpcs;
    public static HashMap varStr = new HashMap();
    public static HashMap varDouble = new HashMap();
    public static HashMap OPSvariables = new HashMap();
    public static double[][][] varDoubleArray = new double[100][4][4];
    public static ArrayList<Object[]> varObjectArray = new ArrayList<Object[]>(100);
    public static JSONObject[] ChannelMetadata_ = new JSONObject[0];
    public static JSONObject SummaryMetadata = new JSONObject();
}
