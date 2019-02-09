/**
 * AdministradorWSDL_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.atwebpages.lobo11235.AdministradorWSDL;

public interface AdministradorWSDL_PortType extends java.rmi.Remote {
    public java.lang.String appendAdmin(com.atwebpages.lobo11235.AdministradorWSDL.Admin admin) throws java.rmi.RemoteException;
    public java.lang.String editAdmin(com.atwebpages.lobo11235.AdministradorWSDL.Admin admin, java.lang.String correo) throws java.rmi.RemoteException;
    public java.lang.String deleteAdmin(java.lang.String correo) throws java.rmi.RemoteException;
    public com.atwebpages.lobo11235.AdministradorWSDL.Admin[] getAdminList() throws java.rmi.RemoteException;
}
