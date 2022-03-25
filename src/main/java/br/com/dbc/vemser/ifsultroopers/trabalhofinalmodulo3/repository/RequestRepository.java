package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.repository;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request.RequestUpdateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.RequestEntity;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.exception.BusinessRuleException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class RequestRepository {

    public static List<RequestEntity> list = new ArrayList<>();
    public static List<RequestEntity> closedList = new ArrayList<>();
    public static AtomicInteger COUNTER = new AtomicInteger();

    public RequestRepository() {
        list.add(new RequestEntity(COUNTER.incrementAndGet(), "Minha casa foi alagada pela enchente", "Minha casa foi alagada pela enchente que teve aqui na cidada, perdi todas as minhas coisas, não tenho nem comida para alimentar meus filhos, preciso urgentemente de ajuda para poder comprar nossas coisas e podermos recomeçar", 5000.0, 300.00, 1, 1));
        closedList.add(new RequestEntity(COUNTER.incrementAndGet(), "Estou sem dinheiro para comprar remédios para minha filha", "Este mês ainda não recebi por conta do atraso dos pagamentos na minha empresa, e tenho em casa uma filha de 3 anos que precisa de um certo remédio que é caro por conta de uma doença que ela tem, estou desesperada e preciso de ajuda, pois tenho medo que algo aconteça com ela", 369.50, 400.00, 1, 2));
        list.add(new RequestEntity(COUNTER.incrementAndGet(), "Preciso de ajuda para realizar um sonho", "Com muito esforço consegui uma vaga em uma faculdade na Suiça, mas minha familía é muito cara, e não tenho dinheiro", 30000.0, 50.00, 2, 3));
        list.add(new RequestEntity(COUNTER.incrementAndGet(), "Cai e quebrei minha clavicula", "Estava andando de bicicleta, quando cai numa vala, e com isso quebrei minha clavicula, a cirurgia é cara e atualmente não tenho dinheiro, preciso de ajuda para poder voltar a pedalar e me quebrar mais.", 12000.0, 10.00, 2, 4));
    }

    public RequestRepository(boolean some) {}

    public List<RequestEntity> getAll() {
        return list;
    }

    public RequestEntity getById(Integer id) throws Exception {
        return list.stream()
                .filter(request -> request.getIdRequest().equals(id))
                .findFirst()
                .orElseThrow(() -> new BusinessRuleException("Vakinha não encontrada."));
    }

    public RequestEntity create(Integer id, RequestEntity requestEntity) {
        requestEntity.setIdUser(id);
        requestEntity.setIdRequest(COUNTER.incrementAndGet());
        requestEntity.setReachedValue(0.0);
        list.add(requestEntity);
        return requestEntity;
    }

    public RequestEntity update(Integer id, RequestUpdateDTO newData) throws Exception {
        RequestEntity requestEntity = this.getById(id);

        String updatedTitle = newData.getTitle();
        String updatedDescription = newData.getDescription();
        Double updatedGoal = newData.getGoal();

        if (updatedTitle != null) {
            requestEntity.setTitle(updatedTitle);
        }

        if (updatedDescription != null) {
            requestEntity.setDescription(updatedDescription);
        }

        if (updatedGoal != null) {
            requestEntity.setGoal(updatedGoal);

            if (requestEntity.getReachedValue() >= requestEntity.getGoal()) {
                list.remove(requestEntity);
                closedList.add(requestEntity);
            }
        }

        return requestEntity;
    }

    public RequestEntity delete(Integer id) throws Exception {
        RequestEntity requestEntity = this.getById(id);
        list.remove(requestEntity);
        return requestEntity;
    }

    public RequestEntity incrementReachedValue(Integer id, Double value) throws Exception {
        RequestEntity requestEntity = this.getById(id);
        requestEntity.setReachedValue(requestEntity.getReachedValue() + value);

        if (requestEntity.getReachedValue() >= requestEntity.getGoal()) {
            list.remove(requestEntity);
            closedList.add(requestEntity);
        }

        return requestEntity;
    }

    public List<RequestEntity> getClosedList() {
        return closedList;
    }

    public List<RequestEntity> getByCategory(Integer id) {
        return list.stream()
                .filter(request -> request.getIdCategory().equals(id))
                .toList();
    }

    public List<RequestEntity> deleteAll(Integer id) throws Exception {
        List<RequestEntity> removeds = new ArrayList<>();
        list.stream()
                .filter(request -> request.getIdUser().equals(id))
                .forEach(request -> list.add(request));
        list.removeAll(removeds);
        return removeds;
    }
}
