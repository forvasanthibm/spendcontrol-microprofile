package com.ibm.spendcontrol.microprofile.persistence;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import com.ibm.spendcontrol.microprofile.bean.Spend;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteResult;

@ApplicationScoped
public class SpendDAO {
	
	@Inject
	@ConfigProperty(name="mp.mongodb.host")
	private String DBHost;
	
	@Inject
	@ConfigProperty(name="mp.mongodb.port")
	private int DBPort;
	
	@Inject
	@ConfigProperty(name="mp.mongodb")
	private String mongoDBName;
	
	@Inject
	@ConfigProperty(name="mp.mongodb.collection")
	private String mongoColName;
	
	@Inject
	@ConfigProperty(name="mp.mongodb.user")
	private String mongoUser;
	
	@Inject
	@ConfigProperty(name="mp.mongodb.password")
	private String mongoPassword;
	
	private MongoClient mongo;
	private DB db;
	private DBCollection col;
	
	public SpendDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public void initDBConnection() throws UnknownHostException {
		
		System.out.println("=======================>>>>"+DBHost);
		System.out.println("=======================>>>>"+DBPort);
		System.out.println("=======================>>>>"+mongoDBName);
		System.out.println("=======================>>>>"+mongoColName);
		System.out.println("=======================>>>>"+mongoUser);
		System.out.println("=======================>>>>"+mongoPassword);

		MongoCredential auth = MongoCredential.createScramSha1Credential(mongoUser, mongoDBName, mongoPassword.toCharArray());
		List<MongoCredential> auths = new ArrayList<MongoCredential>();
		auths.add(auth);
		
		ServerAddress serverAddress = new ServerAddress(DBHost, DBPort);
		mongo = new MongoClient(serverAddress, auths);
		
		db = mongo.getDB(mongoDBName);
		col = db.getCollection(mongoColName);	
	}
	
	public void checkDBConnection() {
		if(col != null && col instanceof DBCollection) {
			//do nothing
		}
		else {
			try {
				initDBConnection();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void createSpend(Spend spend) {
		checkDBConnection();
		DBObject doc = createDBObject(spend);
				
		WriteResult result = col.insert(doc);
		System.out.println("======================");
		System.out.println(result.getUpsertedId());
		System.out.println(result.getN());
		System.out.println(result.isUpdateOfExisting());
		System.out.println("======================");
		
	}
	
	public boolean updateSpend(Spend spend, String id) {
		checkDBConnection();
		boolean result = false;
		List<Object> list = this.findSpendById(id);
		if(!list.isEmpty()) {
			spend.setId(id);
			DBObject obj = createDBObject(spend);
			DBObject query = BasicDBObjectBuilder.start().add("_id", spend.getId()).get();
			WriteResult r = col.update(query, obj);
			result = r.isUpdateOfExisting();
		}
		return result;
		
	}
	
	public void deleteSpend(String id) {
		checkDBConnection();
		DBObject query = BasicDBObjectBuilder.start().add("_id", id).get();
		WriteResult result = col.remove(query);
		
	}
	
	public List<Object> getAllSpends(){
		checkDBConnection();
		List<Object> list = new ArrayList<>();
		DBCursor cursor = col.find();
		while(cursor.hasNext()){
			list.add(cursor.next());
		}
		return list;
	}
	
	public List<Object> findSpendById(String id){
		checkDBConnection();
		List<Object> list = new ArrayList<>();
		DBObject query = BasicDBObjectBuilder.start().add("_id", id).get();
		DBCursor cur = col.find(query);
		if(cur.hasNext()) {
			list.add(cur.next());
		}
		return list;
		
	}
	
	private static DBObject createDBObject(Spend spend) {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
								
		docBuilder.append("_id", spend.getId());
		docBuilder.append("email", spend.getEmail());
		docBuilder.append("spendDate", spend.getSpendDate());
		docBuilder.append("spendType", spend.getSpendType());
		docBuilder.append("category", spend.getCategory());
		docBuilder.append("vendor", spend.getVendor());
		docBuilder.append("invoiceAmount", spend.getInvoiceAmount());
		return docBuilder.get();
	}
	

}


