package com.example.demo.entity;
public class Permission {
private Long id;
private String permissionKey;
private boolean active;
public Permission() {}
public Permission(Long id, String permissionKey, boolean active) {
this.id = id;
this.permissionKey = permissionKey;
this.active = active;
}
public Long getId() { return id; }
public void setId(Long id) { this.id = id; }
public String getPermissionKey() { return permissionKey; }
public void setPermissionKey(String permissionKey) { this.permissionKey = permissionKey; }
public boolean isActive() { return active; }
public void setActive(boolean active) { this.active = active; }
}