package data.dao;

import data.dto.ConsultantDto;

public interface ConsultantDao {

    ConsultantDto getConsultantByName(String name);

    void saveOrUpdateConsultant(ConsultantDto consultantDto);

}
