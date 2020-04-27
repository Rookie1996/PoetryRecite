package com.xjr.model;

public class GradeView {
    @Override
	public String toString() {
		return "GradeView [vid=" + vid + ", vuserid=" + vuserid + ", vusername=" + vusername + ", vtestid=" + vtestid
				+ ", vtestype=" + vtestype + ", vcontent=" + vcontent + ", vanswer=" + vanswer + ", vuseranswer="
				+ vuseranswer + ", vgrade=" + vgrade + "]";
	}

	private Integer vid;

    private Long vuserid;

    private String vusername;

    private Integer vtestid;

    private String vtestype;

    private String vcontent;

    private String vanswer;

    private String vuseranswer;

    private Integer vgrade;

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Long getVuserid() {
        return vuserid;
    }

    public void setVuserid(Long vuserid) {
        this.vuserid = vuserid;
    }

    public String getVusername() {
        return vusername;
    }

    public void setVusername(String vusername) {
        this.vusername = vusername == null ? null : vusername.trim();
    }

    public Integer getVtestid() {
        return vtestid;
    }

    public void setVtestid(Integer vtestid) {
        this.vtestid = vtestid;
    }

    public String getVtestype() {
        return vtestype;
    }

    public void setVtestype(String vtestype) {
        this.vtestype = vtestype == null ? null : vtestype.trim();
    }

    public String getVcontent() {
        return vcontent;
    }

    public void setVcontent(String vcontent) {
        this.vcontent = vcontent == null ? null : vcontent.trim();
    }

    public String getVanswer() {
        return vanswer;
    }

    public void setVanswer(String vanswer) {
        this.vanswer = vanswer == null ? null : vanswer.trim();
    }

    public String getVuseranswer() {
        return vuseranswer;
    }

    public void setVuseranswer(String vuseranswer) {
        this.vuseranswer = vuseranswer == null ? null : vuseranswer.trim();
    }

    public Integer getVgrade() {
        return vgrade;
    }

    public void setVgrade(Integer vgrade) {
        this.vgrade = vgrade;
    }
}