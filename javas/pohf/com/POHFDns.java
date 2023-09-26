package pohf.com;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class POHFDns extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50611916806100206000396000f3fe608060405234801561001057600080fd5b50600436106101425760003560e01c80639010d07c116100b8578063ca15c8731161007c578063ca15c87314610284578063ccf1454a14610297578063d547741f146102aa578063dbfcec10146102bd578063e63ab1e9146102de578063ec87621c146102f357610142565b80639010d07c1461022357806391d148541461024e57806395d89b4114610261578063a217fddf14610269578063a815ff151461027157610142565b80632f2ff15d1161010a5780632f2ff15d146101cd57806336568abe146101e25780633f4ba83a146101f55780634cd88b76146101fd5780635c975abb146102105780638456cb591461021b57610142565b806301ffc9a71461014757806306661abd1461016f57806306fdde0314610182578063248a9ca314610197578063261a323e146101ba575b600080fd5b61015a610155366004611575565b61031a565b60405190151581526020015b60405180910390f35b610101545b604051908152602001610166565b61018a610347565b6040516101669190611739565b6101746101a5366004611511565b60009081526065602052604090206001015490565b61015a6101c836600461159d565b6103d9565b6101e06101db366004611529565b610405565b005b6101e06101f0366004611529565b61042c565b6101e061044e565b6101e061020b36600461161b565b6104d3565b60c95460ff1661015a565b6101e0610553565b610236610231366004611554565b6105cf565b6040516001600160a01b039091168152602001610166565b61015a61025c366004611529565b6105f0565b61018a61061b565b610174600081565b6101e061027f3660046115d8565b61062a565b610174610292366004611511565b610892565b6102366102a536600461159d565b6108a9565b6101e06102b8366004611529565b61091f565b6102d06102cb366004611511565b610929565b60405161016692919061174c565b6101746000805160206118c183398151915281565b6101747f241ecf16d79d0f8dbfb92cbc07fe17840425976cf0667f022fe9877caa831b0881565b60006001600160e01b03198216635a05180f60e01b148061033f575061033f82610a0d565b90505b919050565b606060fb805461035690611859565b80601f016020809104026020016040519081016040528092919081815260200182805461038290611859565b80156103cf5780601f106103a4576101008083540402835291602001916103cf565b820191906000526020600020905b8154815290600101906020018083116103b257829003601f168201915b5050505050905090565b600060fe826040516103eb91906116a8565b9081526040519081900360200190205460ff169050919050565b61040f8282610a42565b60008281526097602052604090206104279082610a69565b505050565b6104368282610a7e565b60008281526097602052604090206104279082610afc565b6104666000805160206118c18339815191523361025c565b6104c95760405162461bcd60e51b815260206004820152602960248201527f504f4846446e733a206d75737420686176652070617573657220726f6c6520746044820152686f20756e706175736560b81b60648201526084015b60405180910390fd5b6104d1610b11565b565b600054610100900460ff16806104ec575060005460ff16155b6105085760405162461bcd60e51b81526004016104c090611776565b600054610100900460ff16158015610533576000805460ff1961ff0019909116610100171660011790555b61053d8383610ba4565b8015610427576000805461ff0019169055505050565b61056b6000805160206118c18339815191523361025c565b6105c75760405162461bcd60e51b815260206004820152602760248201527f504f4846446e733a206d75737420686176652070617573657220726f6c6520746044820152666f20706175736560c81b60648201526084016104c0565b6104d1610c1e565b60008281526097602052604081206105e79083610c99565b90505b92915050565b60009182526065602090815260408084206001600160a01b0393909316845291905290205460ff1690565b606060fc805461035690611859565b60c95460ff16156106705760405162461bcd60e51b815260206004820152601060248201526f14185d5cd8589b194e881c185d5cd95960821b60448201526064016104c0565b61069a7f241ecf16d79d0f8dbfb92cbc07fe17840425976cf0667f022fe9877caa831b083361025c565b6106f45760405162461bcd60e51b815260206004820152602560248201527f504f4846446e733a206d75737420686176652070617573657220726f6c6520746044820152641bc81cd95d60da1b60648201526084016104c0565b60fe8260405161070491906116a8565b9081526040519081900360200190205460ff1615610777578060ff600060fd8560405161073191906116a8565b908152602001604051809103902054815260200190815260200160002060006101000a8154816001600160a01b0302191690836001600160a01b0316021790555061082c565b6101015460fd8360405161078b91906116a8565b9081526040805160209281900383019020929092556101018054600090815260ff835283812080546001600160a01b0319166001600160a01b0387161790559054815261010082529190912083516107e5928501906113da565b50600160fe836040516107f891906116a8565b908152604051908190036020019020805491151560ff19909216919091179055610101546108279060016117c4565b610101555b8160405161083a91906116a8565b60405180910390207f3c3a120919c3d2626b5616080e13c0cdf4a8576b6ad3029416e3b0e5219e02668261086b3390565b604080516001600160a01b0393841681529290911660208301520160405180910390a25050565b600081815260976020526040812061033f90610ca5565b600060fe826040516108bb91906116a8565b9081526040519081900360200190205460ff16156109175760ff600060fd846040516108e791906116a8565b90815260408051602092819003830190205483529082019290925201600020546001600160a01b03169050610342565b506000919050565b6104368282610caf565b60606000610101548310156109f55760008381526101006020908152604080832060ff9092529091205481546001600160a01b0390911690829061096c90611859565b80601f016020809104026020016040519081016040528092919081815260200182805461099890611859565b80156109e55780601f106109ba576101008083540402835291602001916109e5565b820191906000526020600020905b8154815290600101906020018083116109c857829003601f168201915b5050505050915091509150610a08565b5050604080516020810190915260008082525b915091565b60006001600160e01b03198216637965db0b60e01b148061033f57506301ffc9a760e01b6001600160e01b031983161461033f565b600082815260656020526040902060010154610a5f81335b610cd5565b6104278383610d39565b60006105e7836001600160a01b038416610dbf565b6001600160a01b0381163314610aee5760405162461bcd60e51b815260206004820152602f60248201527f416363657373436f6e74726f6c3a2063616e206f6e6c792072656e6f756e636560448201526e103937b632b9903337b91039b2b63360891b60648201526084016104c0565b610af88282610e0e565b5050565b60006105e7836001600160a01b038416610e75565b60c95460ff16610b5a5760405162461bcd60e51b815260206004820152601460248201527314185d5cd8589b194e881b9bdd081c185d5cd95960621b60448201526064016104c0565b60c9805460ff191690557f5db9ee0a495bf2e6ff9c91a7834c1ba4fdd244a5e8aa4e537bd38aeae4b073aa335b6040516001600160a01b03909116815260200160405180910390a1565b600054610100900460ff1680610bbd575060005460ff16155b610bd95760405162461bcd60e51b81526004016104c090611776565b600054610100900460ff16158015610c04576000805460ff1961ff0019909116610100171660011790555b610c0c610f8c565b610c1461100b565b61053d838361107f565b60c95460ff1615610c645760405162461bcd60e51b815260206004820152601060248201526f14185d5cd8589b194e881c185d5cd95960821b60448201526064016104c0565b60c9805460ff191660011790557f62e78cea01bee320cd4e420270b5ea74000d11b0c9f74754ebdbfc544b05a258610b873390565b60006105e78383611155565b600061033f825490565b600082815260656020526040902060010154610ccb8133610a5a565b6104278383610e0e565b610cdf82826105f0565b610af857610cf7816001600160a01b031660146111e9565b610d028360206111e9565b604051602001610d139291906116c4565b60408051601f198184030181529082905262461bcd60e51b82526104c091600401611739565b610d4382826105f0565b610af85760008281526065602090815260408083206001600160a01b03851684529091529020805460ff19166001179055610d7b3390565b6001600160a01b0316816001600160a01b0316837f2f8788117e7eff1d82e926ec794901d17c78024a50270940304540a733656f0d60405160405180910390a45050565b6000818152600183016020526040812054610e06575081546001818101845560008481526020808220909301849055845484825282860190935260409020919091556105ea565b5060006105ea565b610e1882826105f0565b15610af85760008281526065602090815260408083206001600160a01b0385168085529252808320805460ff1916905551339285917ff6391f5c32d9c69d2a47ea670b442974b53935d1edc7fd64eb21e047a839171b9190a45050565b60008181526001830160205260408120548015610f82576000610e996001836117fb565b8554909150600090610ead906001906117fb565b90506000866000018281548110610ed457634e487b7160e01b600052603260045260246000fd5b9060005260206000200154905080876000018481548110610f0557634e487b7160e01b600052603260045260246000fd5b600091825260208083209091019290925582815260018901909152604090208490558654879080610f4657634e487b7160e01b600052603160045260246000fd5b600190038181906000526020600020016000905590558660010160008781526020019081526020016000206000905560019450505050506105ea565b60009150506105ea565b600054610100900460ff1680610fa5575060005460ff16155b610fc15760405162461bcd60e51b81526004016104c090611776565b600054610100900460ff16158015610fec576000805460ff1961ff0019909116610100171660011790555b60c9805460ff191690558015611008576000805461ff00191690555b50565b600054610100900460ff1680611024575060005460ff16155b6110405760405162461bcd60e51b81526004016104c090611776565b600054610100900460ff1615801561106b576000805460ff1961ff0019909116610100171660011790555b8015611008576000805461ff001916905550565b600054610100900460ff1680611098575060005460ff16155b6110b45760405162461bcd60e51b81526004016104c090611776565b600054610100900460ff161580156110df576000805460ff1961ff0019909116610100171660011790555b82516110f29060fb9060208601906113da565b5081516111069060fc9060208501906113da565b506111136000335b6113cb565b61113d7f241ecf16d79d0f8dbfb92cbc07fe17840425976cf0667f022fe9877caa831b083361110e565b61053d6000805160206118c18339815191523361110e565b815460009082106111b35760405162461bcd60e51b815260206004820152602260248201527f456e756d657261626c655365743a20696e646578206f7574206f6620626f756e604482015261647360f01b60648201526084016104c0565b8260000182815481106111d657634e487b7160e01b600052603260045260246000fd5b9060005260206000200154905092915050565b606060006111f88360026117dc565b6112039060026117c4565b67ffffffffffffffff81111561122957634e487b7160e01b600052604160045260246000fd5b6040519080825280601f01601f191660200182016040528015611253576020820181803683370190505b509050600360fc1b8160008151811061127c57634e487b7160e01b600052603260045260246000fd5b60200101906001600160f81b031916908160001a905350600f60fb1b816001815181106112b957634e487b7160e01b600052603260045260246000fd5b60200101906001600160f81b031916908160001a90535060006112dd8460026117dc565b6112e89060016117c4565b90505b600181111561137c576f181899199a1a9b1b9c1cb0b131b232b360811b85600f166010811061132a57634e487b7160e01b600052603260045260246000fd5b1a60f81b82828151811061134e57634e487b7160e01b600052603260045260246000fd5b60200101906001600160f81b031916908160001a90535060049490941c9361137581611842565b90506112eb565b5083156105e75760405162461bcd60e51b815260206004820181905260248201527f537472696e67733a20686578206c656e67746820696e73756666696369656e7460448201526064016104c0565b61040f8282610af88282610d39565b8280546113e690611859565b90600052602060002090601f016020900481019282611408576000855561144e565b82601f1061142157805160ff191683800117855561144e565b8280016001018555821561144e579182015b8281111561144e578251825591602001919060010190611433565b5061145a92915061145e565b5090565b5b8082111561145a576000815560010161145f565b80356001600160a01b038116811461034257600080fd5b600082601f83011261149a578081fd5b813567ffffffffffffffff808211156114b5576114b56118aa565b604051601f8301601f19908116603f011681019082821181831017156114dd576114dd6118aa565b816040528381528660208588010111156114f5578485fd5b8360208701602083013792830160200193909352509392505050565b600060208284031215611522578081fd5b5035919050565b6000806040838503121561153b578081fd5b8235915061154b60208401611473565b90509250929050565b60008060408385031215611566578182fd5b50508035926020909101359150565b600060208284031215611586578081fd5b81356001600160e01b0319811681146105e7578182fd5b6000602082840312156115ae578081fd5b813567ffffffffffffffff8111156115c4578182fd5b6115d08482850161148a565b949350505050565b600080604083850312156115ea578182fd5b823567ffffffffffffffff811115611600578283fd5b61160c8582860161148a565b92505061154b60208401611473565b6000806040838503121561162d578182fd5b823567ffffffffffffffff80821115611644578384fd5b6116508683870161148a565b93506020850135915080821115611665578283fd5b506116728582860161148a565b9150509250929050565b60008151808452611694816020860160208601611812565b601f01601f19169290920160200192915050565b600082516116ba818460208701611812565b9190910192915050565b60007f416363657373436f6e74726f6c3a206163636f756e7420000000000000000000825283516116fc816017850160208801611812565b7001034b99036b4b9b9b4b733903937b6329607d1b601791840191820152835161172d816028840160208801611812565b01602801949350505050565b6000602082526105e7602083018461167c565b60006040825261175f604083018561167c565b905060018060a01b03831660208301529392505050565b6020808252602e908201527f496e697469616c697a61626c653a20636f6e747261637420697320616c72656160408201526d191e481a5b9a5d1a585b1a5e995960921b606082015260800190565b600082198211156117d7576117d7611894565b500190565b60008160001904831182151516156117f6576117f6611894565b500290565b60008282101561180d5761180d611894565b500390565b60005b8381101561182d578181015183820152602001611815565b8381111561183c576000848401525b50505050565b60008161185157611851611894565b506000190190565b60028104600182168061186d57607f821691505b6020821081141561188e57634e487b7160e01b600052602260045260246000fd5b50919050565b634e487b7160e01b600052601160045260246000fd5b634e487b7160e01b600052604160045260246000fdfe65d7a28e3265b37a6474929f336521b332c1681b933f6cb9f3376673440d862aa264697066735822122063a9817b2223b910bc1785c9ae56839b9afa3d7551fa39d0d8f98b3932807b9564736f6c63430008020033";

    public static final String FUNC_DEFAULT_ADMIN_ROLE = "DEFAULT_ADMIN_ROLE";

    public static final String FUNC_MANAGER_ROLE = "MANAGER_ROLE";

    public static final String FUNC_PAUSER_ROLE = "PAUSER_ROLE";

    public static final String FUNC_ADDRESSOF = "addressOf";

    public static final String FUNC_COUNT = "count";

    public static final String FUNC_EXISTS = "exists";

    public static final String FUNC_GETROLEADMIN = "getRoleAdmin";

    public static final String FUNC_GETROLEMEMBER = "getRoleMember";

    public static final String FUNC_GETROLEMEMBERCOUNT = "getRoleMemberCount";

    public static final String FUNC_GRANTROLE = "grantRole";

    public static final String FUNC_HASROLE = "hasRole";

    public static final String FUNC_HOSTOF = "hostOf";

    public static final String FUNC_INITIALIZE = "initialize";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_PAUSE = "pause";

    public static final String FUNC_PAUSED = "paused";

    public static final String FUNC_RENOUNCEROLE = "renounceRole";

    public static final String FUNC_REVOKEROLE = "revokeRole";

    public static final String FUNC_SET = "set";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_UNPAUSE = "unpause";

    public static final Event DEL_EVENT = new Event("Del", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>(true) {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event PAUSED_EVENT = new Event("Paused", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final Event ROLEADMINCHANGED_EVENT = new Event("RoleAdminChanged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Bytes32>(true) {}, new TypeReference<Bytes32>(true) {}));
    ;

    public static final Event ROLEGRANTED_EVENT = new Event("RoleGranted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event ROLEREVOKED_EVENT = new Event("RoleRevoked", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event SET_EVENT = new Event("Set", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>(true) {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event UNPAUSED_EVENT = new Event("Unpaused", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected POHFDns(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected POHFDns(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected POHFDns(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected POHFDns(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<DelEventResponse> getDelEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DEL_EVENT, transactionReceipt);
        ArrayList<DelEventResponse> responses = new ArrayList<DelEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DelEventResponse typedResponse = new DelEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.name = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.manager = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<DelEventResponse> delEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, DelEventResponse>() {
            @Override
            public DelEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(DEL_EVENT, log);
                DelEventResponse typedResponse = new DelEventResponse();
                typedResponse.log = log;
                typedResponse.name = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.manager = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<DelEventResponse> delEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DEL_EVENT));
        return delEventFlowable(filter);
    }

    public List<PausedEventResponse> getPausedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PAUSED_EVENT, transactionReceipt);
        ArrayList<PausedEventResponse> responses = new ArrayList<PausedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PausedEventResponse typedResponse = new PausedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PausedEventResponse> pausedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, PausedEventResponse>() {
            @Override
            public PausedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PAUSED_EVENT, log);
                PausedEventResponse typedResponse = new PausedEventResponse();
                typedResponse.log = log;
                typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PausedEventResponse> pausedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PAUSED_EVENT));
        return pausedEventFlowable(filter);
    }

    public List<RoleAdminChangedEventResponse> getRoleAdminChangedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ROLEADMINCHANGED_EVENT, transactionReceipt);
        ArrayList<RoleAdminChangedEventResponse> responses = new ArrayList<RoleAdminChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RoleAdminChangedEventResponse typedResponse = new RoleAdminChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.previousAdminRole = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.newAdminRole = (byte[]) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RoleAdminChangedEventResponse> roleAdminChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RoleAdminChangedEventResponse>() {
            @Override
            public RoleAdminChangedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ROLEADMINCHANGED_EVENT, log);
                RoleAdminChangedEventResponse typedResponse = new RoleAdminChangedEventResponse();
                typedResponse.log = log;
                typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.previousAdminRole = (byte[]) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.newAdminRole = (byte[]) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RoleAdminChangedEventResponse> roleAdminChangedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ROLEADMINCHANGED_EVENT));
        return roleAdminChangedEventFlowable(filter);
    }

    public List<RoleGrantedEventResponse> getRoleGrantedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ROLEGRANTED_EVENT, transactionReceipt);
        ArrayList<RoleGrantedEventResponse> responses = new ArrayList<RoleGrantedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RoleGrantedEventResponse typedResponse = new RoleGrantedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.sender = (String) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RoleGrantedEventResponse> roleGrantedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RoleGrantedEventResponse>() {
            @Override
            public RoleGrantedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ROLEGRANTED_EVENT, log);
                RoleGrantedEventResponse typedResponse = new RoleGrantedEventResponse();
                typedResponse.log = log;
                typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.sender = (String) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RoleGrantedEventResponse> roleGrantedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ROLEGRANTED_EVENT));
        return roleGrantedEventFlowable(filter);
    }

    public List<RoleRevokedEventResponse> getRoleRevokedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ROLEREVOKED_EVENT, transactionReceipt);
        ArrayList<RoleRevokedEventResponse> responses = new ArrayList<RoleRevokedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RoleRevokedEventResponse typedResponse = new RoleRevokedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.sender = (String) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RoleRevokedEventResponse> roleRevokedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RoleRevokedEventResponse>() {
            @Override
            public RoleRevokedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ROLEREVOKED_EVENT, log);
                RoleRevokedEventResponse typedResponse = new RoleRevokedEventResponse();
                typedResponse.log = log;
                typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.sender = (String) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RoleRevokedEventResponse> roleRevokedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ROLEREVOKED_EVENT));
        return roleRevokedEventFlowable(filter);
    }

    public List<SetEventResponse> getSetEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SET_EVENT, transactionReceipt);
        ArrayList<SetEventResponse> responses = new ArrayList<SetEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SetEventResponse typedResponse = new SetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.name = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.manager = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<SetEventResponse> setEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, SetEventResponse>() {
            @Override
            public SetEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SET_EVENT, log);
                SetEventResponse typedResponse = new SetEventResponse();
                typedResponse.log = log;
                typedResponse.name = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.manager = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<SetEventResponse> setEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SET_EVENT));
        return setEventFlowable(filter);
    }

    public List<UnpausedEventResponse> getUnpausedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(UNPAUSED_EVENT, transactionReceipt);
        ArrayList<UnpausedEventResponse> responses = new ArrayList<UnpausedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            UnpausedEventResponse typedResponse = new UnpausedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<UnpausedEventResponse> unpausedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, UnpausedEventResponse>() {
            @Override
            public UnpausedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(UNPAUSED_EVENT, log);
                UnpausedEventResponse typedResponse = new UnpausedEventResponse();
                typedResponse.log = log;
                typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<UnpausedEventResponse> unpausedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(UNPAUSED_EVENT));
        return unpausedEventFlowable(filter);
    }

    public RemoteFunctionCall<byte[]> DEFAULT_ADMIN_ROLE() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DEFAULT_ADMIN_ROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<byte[]> MANAGER_ROLE() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_MANAGER_ROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<byte[]> PAUSER_ROLE() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_PAUSER_ROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<String> addressOf(String name_) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ADDRESSOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name_)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> count() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_COUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> exists(String name_) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_EXISTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name_)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<byte[]> getRoleAdmin(byte[] role) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETROLEADMIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<String> getRoleMember(byte[] role, BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETROLEMEMBER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getRoleMemberCount(byte[] role) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETROLEMEMBERCOUNT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> grantRole(byte[] role, String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_GRANTROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.web3j.abi.datatypes.Address(160, account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> hasRole(byte[] role, String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_HASROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Tuple2<String, String>> hostOf(BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_HOSTOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
        return new RemoteFunctionCall<Tuple2<String, String>>(function,
                new Callable<Tuple2<String, String>>() {
                    @Override
                    public Tuple2<String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> initialize(String name_, String symbol_) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INITIALIZE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name_), 
                new org.web3j.abi.datatypes.Utf8String(symbol_)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> pause() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PAUSE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> paused() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_PAUSED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceRole(byte[] role, String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.web3j.abi.datatypes.Address(160, account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> revokeRole(byte[] role, String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REVOKEROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.web3j.abi.datatypes.Address(160, account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> set(String name_, String addr_) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SET, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name_), 
                new org.web3j.abi.datatypes.Address(160, addr_)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> unpause() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UNPAUSE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static POHFDns load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new POHFDns(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static POHFDns load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new POHFDns(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static POHFDns load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new POHFDns(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static POHFDns load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new POHFDns(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<POHFDns> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(POHFDns.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<POHFDns> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(POHFDns.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<POHFDns> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(POHFDns.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<POHFDns> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(POHFDns.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class DelEventResponse extends BaseEventResponse {
        public byte[] name;

        public String addr;

        public String manager;
    }

    public static class PausedEventResponse extends BaseEventResponse {
        public String account;
    }

    public static class RoleAdminChangedEventResponse extends BaseEventResponse {
        public byte[] role;

        public byte[] previousAdminRole;

        public byte[] newAdminRole;
    }

    public static class RoleGrantedEventResponse extends BaseEventResponse {
        public byte[] role;

        public String account;

        public String sender;
    }

    public static class RoleRevokedEventResponse extends BaseEventResponse {
        public byte[] role;

        public String account;

        public String sender;
    }

    public static class SetEventResponse extends BaseEventResponse {
        public byte[] name;

        public String addr;

        public String manager;
    }

    public static class UnpausedEventResponse extends BaseEventResponse {
        public String account;
    }
}
