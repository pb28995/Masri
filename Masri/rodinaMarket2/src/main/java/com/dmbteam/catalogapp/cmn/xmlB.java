package com.dmbteam.catalogapp.cmn;

import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
/**
 * The Class Catalog.
 */
@Root(strict=false,name = "xml")
public class xmlB {

    @ElementList
    public List<Branch> branches;

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public Branch getMyBranCh(){

        return  getBranches().get(0);
    }
}
