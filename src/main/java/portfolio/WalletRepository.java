package portfolio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface WalletRepository extends Repository<Wallet, Long> {

    Wallet add(Wallet Wallet);

    void remove(Wallet wallet);
}

class InMemoryWalletRepository implements WalletRepository {

    private final Map<Long, Wallet> database = new HashMap<>();
    private Long id = 0L;

    @Override
    public Wallet add(Wallet wallet) {
        boolean walletAlreadyExist = database.values().stream().anyMatch(w -> w.getName().equals(wallet.getName()));
        if (walletAlreadyExist) {
            throw new DuplicateWalletNameException(wallet.getName());
        }
        return database.put(++id, wallet);
    }

    @Override
    public void remove(Wallet wallet) {
        database.values().remove(wallet);
    }

    @Override
    public List<Wallet> findAll() {
        return new ArrayList<>(database.values());
    }
}