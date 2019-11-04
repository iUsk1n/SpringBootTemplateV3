package com.market.sapphires.sbt.v3.service;

import org.springframework.stereotype.Service;

import com.market.sapphires.sbt.v3.entity.LoginUser;
import com.market.sapphires.sbt.v3.form.LoginUserDetailForm;
import com.market.sapphires.sbt.v3.form.datatables.request.UsersDataTablesRequest;
import com.market.sapphires.sbt.v3.form.datatables.response.UserDataTables;
import com.market.sapphires.sbt.v3.mapper.LoginUserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final LoginUserMapper dao;

    public LoginUserDetailForm getDetail(Long id) {
        LoginUser user = this.dao.selectOne(id);

        if (user == null) {
            // TODO TargetNotFoundException
        }

        return new LoginUserDetailForm(user);
    }

    public UserDataTables getList(UsersDataTablesRequest request) {
        UserDataTables dts = new UserDataTables();
        dts.setDraw(request.getDraw());
        dts.setRecordsTotal(this.dao.count());
        dts.setRecordsFiltered(this.dao.count4DataTables(request));

        // 検索

        /*
        users.forEach(u -> {
            dts.addToData(u);
        });
        */

        return dts;
    }

    /*
    public UserDataTables getList(Map<String, String> params) {
    
        int draw = Integer.parseInt(params.get("draw"));
        int start = Integer.parseInt(params.get("start"));
        int length = Integer.parseInt(params.get("length"));
        List<String> search = params.entrySet().stream()
                .filter(e -> e.getKey().startsWith("columns["))
                .filter(e -> e.getKey().endsWith("][search][value]"))
                .map(e -> e.getValue())
                .collect(Collectors.toList());
        Map<String, String> order = params.entrySet().stream()
                .filter(e -> e.getKey().startsWith("order["))
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    
        UserDataTables.Column[] columns = UserDataTables.Column.values();
    
        List<List<Object>> searches = new ArrayList<>();
        for (int i = 0; i < search.size(); i++) {
            searches.add(Arrays.asList(columns[i], search.get(i)));
        }
    
        List<List<Object>> orders = new ArrayList<>();
        for (int i = 0; i < order.size(); i += 2) {
            String keyCol = new StringBuilder("order[").append(i).append("][column]").toString();
            String keyDir = new StringBuilder("order[").append(i).append("][dir]").toString();
            orders.add(Arrays.asList(columns[Integer.parseInt(order.get(keyCol))], order.get(keyDir)));
        }
    
        List<LoginUser> users = this.dao.find4DataTables(start, length, searches, orders);
    
        UserDataTables dts = new UserDataTables();
        dts.setDraw(draw);
        dts.setRecordsTotal((int) this.dao.count());
        dts.setRecordsFiltered(2); // TODO
    
        dts.getData();
        users.forEach(u -> {
            dts.addToData(u);
        });
    
        return dts;
    }
    
    
    */

}
