package com.patharitech.client.dao;

import com.patharitech.client.co.ClientCO;

public interface ClientDao {
	public boolean addUser(ClientCO objClientCO);
	public boolean updateClient(ClientCO objClientCO);
	public ClientCO searchClient(ClientCO objClientCO);
	public boolean deleteUser(ClientCO objClientCO);

}
