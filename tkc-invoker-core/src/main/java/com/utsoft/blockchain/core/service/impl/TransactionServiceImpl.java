package com.utsoft.blockchain.core.service.impl;
import javax.annotation.PostConstruct;
import org.hyperledger.fabric.sdk.ChaincodeID;
import org.springframework.stereotype.Service;
import com.utsoft.blockchain.api.exception.ServiceProcessException;
import com.utsoft.blockchain.api.pojo.ReqtOrderDto;
import com.utsoft.blockchain.api.pojo.ReqtQueryOrderDto;
import com.utsoft.blockchain.api.pojo.RspQueryResultDto;
import com.utsoft.blockchain.api.pojo.SubmitRspResultDto;
import com.utsoft.blockchain.api.pojo.TkcQueryDetailRspVo;
import com.utsoft.blockchain.api.util.TransactionCmd;
import com.utsoft.blockchain.core.service.AbstractTkcBasicService;
import com.utsoft.blockchain.core.service.ITransactionService;
/**
 * 记账记录链码操作
 * @author hunterfox
 * @date 2017年7月18日
 * @version 1.0.0
 */
@Service
public class TransactionServiceImpl extends AbstractTkcBasicService implements ITransactionService {

	@PostConstruct
	@Override
	public void construtChainCode() {
		init();
	}

	@Override
	public SubmitRspResultDto tranfer(String applyCode,String account_from, String account_to, TransactionCmd cmd, String submitJson,String created) throws ServiceProcessException {
		
		isCheckConnecting(applyCode);
	    ChaincodeID chaincodeID = getChainCode(applyCode);
		if (chaincodeManager.checkChannelActive(chaincodeID)) {
			ReqtOrderDto order = new ReqtOrderDto();
			order.setCmd(cmd.name());
			order.setFromAccount(account_from);
			order.setToAccount(account_to);
			order.setJson(submitJson);
			return chaincodeManager.submitRequest(chaincodeID, order);
		} 
		throw new ServiceProcessException(chaincodeID+":channel not connecting");
	}

	@Override
	public TkcQueryDetailRspVo select(String applyCode,String account_to, TransactionCmd cmd, String created) throws ServiceProcessException {
		 
		 isCheckConnecting(applyCode);
		 ChaincodeID chaincodeID = getChainCode(applyCode);
		 TkcQueryDetailRspVo orderdetail = null;
		 if (chaincodeManager.checkChannelActive(chaincodeID)) {
			ReqtQueryOrderDto queryPojo = new ReqtQueryOrderDto();
			queryPojo.setCmd(cmd.name());
			queryPojo.setToAccount(account_to);
			RspQueryResultDto resultDto = chaincodeManager.query(chaincodeID, queryPojo);
			if (resultDto!=null) {
				orderdetail = new TkcQueryDetailRspVo();
				orderdetail.setPayload(resultDto.getPayload());
				orderdetail.setTimestamp(resultDto.getTimestamp());
			}
			return orderdetail;
		}
	    throw new ServiceProcessException(chaincodeID+":channel not connecting");
	}

	@Override
	public TkcQueryDetailRspVo selectByJson(String applyCode,String account_to, TransactionCmd cmd, String submitJson, String created)
			throws ServiceProcessException {
		 
		isCheckConnecting(applyCode);
		ChaincodeID chaincodeID = getChainCode(applyCode);
		TkcQueryDetailRspVo orderdetail = null;
		if (chaincodeManager.checkChannelActive(chaincodeID)) {
			ReqtQueryOrderDto queryPojo = new ReqtQueryOrderDto();
			queryPojo.setCmd(cmd.name());
			queryPojo.setToAccount(account_to);
			queryPojo.setJson(submitJson);
			RspQueryResultDto result =  chaincodeManager.query(chaincodeID, queryPojo);
			if (result!=null) {
				orderdetail = new TkcQueryDetailRspVo();
				orderdetail.setPayload(result.getPayload());
				orderdetail.setTimestamp(result.getTimestamp());
			}
			return orderdetail;
		}
		throw new ServiceProcessException(chaincodeID+" channel not connecting");
	}
	
	/**
	 * 链码检查和 channel 重连工作
	 * @param applyCode
	 */
    private void isCheckConnecting(String applyCode) {
		
	    ChaincodeID chaincodeID = getChainCode(applyCode);
		if (chaincodeID==null) {
		 	throw new ServiceProcessException(applyCode+" service not exist");
		 } 
		 if (!chaincodeManager.checkChannelActive(chaincodeID)) {
			 chaincodeManager.reconnect(chaincodeID);
		  }
	}
}
