package ${basePackage}.web;

import ${basePackage}.core.ServerResponse;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/*
* @Author  ${author}
 * @Description
 * @Date ${date}
 **/

@RestController
@RequestMapping("${baseRequestMapping}")
@Slf4j
public class ${modelNameUpperCamel}Controller {
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @ApiOperation(value = "新增",notes = "新增")
    @PostMapping
    public ServerResponse add(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return ServerResponse.createBySuccess();
    }

    @ApiOperation(value = "删除",notes = "删除")
    @DeleteMapping("/{id}")
    public ServerResponse delete(@PathVariable Integer id) {
        ${modelNameLowerCamel}Service.deleteById(id);
        return ServerResponse.createBySuccess();
    }

    @ApiOperation(value = "更新",notes = "更新")
    @PutMapping
    public ServerResponse update(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return ServerResponse.createBySuccess();
    }

    @ApiOperation(value = "查询详情",notes = "查询详情")
    @GetMapping("/{id}")
    public ServerResponse detail(@PathVariable Integer id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(id);
        return ServerResponse.createBySuccess(${modelNameLowerCamel});
    }

    @ApiOperation(value = "查询列表",notes = "查询列表")
    @GetMapping
    public ServerResponse list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ServerResponse.createBySuccess(pageInfo);
    }
}
