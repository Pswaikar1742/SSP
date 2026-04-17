package com.greenscale.model;

import java.io.Serializable;

/**
 * JavaBean representing a hardware node (mirrors the `nodes` table).
 */
public class NodeBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer nodeId;
    private String hostname;
    private String specs;
    private String status;

    public NodeBean() {
    }

    public NodeBean(Integer nodeId, String hostname, String specs, String status) {
        this.nodeId = nodeId;
        this.hostname = hostname;
        this.specs = specs;
        this.status = status;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "NodeBean{" +
                "nodeId=" + nodeId +
                ", hostname='" + hostname + '\'' +
                ", specs='" + specs + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
