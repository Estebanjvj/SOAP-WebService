/**
 * AdministradorWSDL_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.atwebpages.lobo11235.AdministradorWSDL;

public class AdministradorWSDL_ServiceLocator extends org.apache.axis.client.Service implements com.atwebpages.lobo11235.AdministradorWSDL.AdministradorWSDL_Service {

    public AdministradorWSDL_ServiceLocator() {
    }


    public AdministradorWSDL_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AdministradorWSDL_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AdministradorWSDLSOAP
    private java.lang.String AdministradorWSDLSOAP_address = "http://lobo11235.atwebpages.com/soapServer.php";

    public java.lang.String getAdministradorWSDLSOAPAddress() {
        return AdministradorWSDLSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AdministradorWSDLSOAPWSDDServiceName = "AdministradorWSDLSOAP";

    public java.lang.String getAdministradorWSDLSOAPWSDDServiceName() {
        return AdministradorWSDLSOAPWSDDServiceName;
    }

    public void setAdministradorWSDLSOAPWSDDServiceName(java.lang.String name) {
        AdministradorWSDLSOAPWSDDServiceName = name;
    }

    public com.atwebpages.lobo11235.AdministradorWSDL.AdministradorWSDL_PortType getAdministradorWSDLSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AdministradorWSDLSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAdministradorWSDLSOAP(endpoint);
    }

    public com.atwebpages.lobo11235.AdministradorWSDL.AdministradorWSDL_PortType getAdministradorWSDLSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.atwebpages.lobo11235.AdministradorWSDL.AdministradorWSDLSOAPStub _stub = new com.atwebpages.lobo11235.AdministradorWSDL.AdministradorWSDLSOAPStub(portAddress, this);
            _stub.setPortName(getAdministradorWSDLSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAdministradorWSDLSOAPEndpointAddress(java.lang.String address) {
        AdministradorWSDLSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.atwebpages.lobo11235.AdministradorWSDL.AdministradorWSDL_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.atwebpages.lobo11235.AdministradorWSDL.AdministradorWSDLSOAPStub _stub = new com.atwebpages.lobo11235.AdministradorWSDL.AdministradorWSDLSOAPStub(new java.net.URL(AdministradorWSDLSOAP_address), this);
                _stub.setPortName(getAdministradorWSDLSOAPWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("AdministradorWSDLSOAP".equals(inputPortName)) {
            return getAdministradorWSDLSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://lobo11235.atwebpages.com/AdministradorWSDL/", "AdministradorWSDL");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://lobo11235.atwebpages.com/AdministradorWSDL/", "AdministradorWSDLSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AdministradorWSDLSOAP".equals(portName)) {
            setAdministradorWSDLSOAPEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
