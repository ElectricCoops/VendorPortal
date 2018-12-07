/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pwr.lcec.vendor.web.view;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class GuestPreferences implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * DEV/PRD
	 */
	private String layout = "moody";
	
	/**
	 * TST
	 */
	//private String layout = "yellow";
    

    private String theme = "bluegrey";
    
    private boolean darkMenu;

    private boolean horizontal = false;
    
    private boolean orientationRTL;
                            
	public String getTheme() {		
		return theme;
	}
    
	public void setTheme(String theme) {
		this.theme = theme;
	}
    
    public String getLayout() {		
        return layout;
    }
    
    public void setLayout(String layout) {
        this.layout = layout;
    }
    
    public boolean isDarkMenu() {		
        return darkMenu;
    }
    
    public void setDarkMenu(boolean darkMenu) {
        this.darkMenu = darkMenu;
    }

    public boolean isOrientationRTL() {
        return orientationRTL;
    }

    public void setOrientationRTL(boolean orientationRTL) {
        this.orientationRTL = orientationRTL;
    }

    public boolean isHorizontal() {
        return this.horizontal;
    }

    public void setHorizontal(boolean value) {
        this.horizontal = value;
    }
}
