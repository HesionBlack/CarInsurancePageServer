<template>
    <div>
        <div>
            <div style="display: flex;justify-content: space-between">
                <div>
                    <el-input placeholder="请输入车辆品牌进行搜索，可以直接回车搜索..." prefix-icon="el-icon-search"
                              clearable
                              @clear="initCars"
                              style="width: 350px;margin-right: 10px" v-model="keyword"
                              @keydown.enter.native="initCars" :disabled="showAdvanceSearchView"></el-input>
                    <el-button icon="el-icon-search" type="primary" @click="initCars" :disabled="showAdvanceSearchView">
                        搜索
                    </el-button>
                    <el-button type="primary" @click="showAdvanceSearchView = !showAdvanceSearchView">
                        <i :class="showAdvanceSearchView?'fa fa-angle-double-up':'fa fa-angle-double-down'"
                           aria-hidden="true"></i>
                        高级搜索
                    </el-button>
                </div>
                <div>
                    <el-button type="primary" icon="el-icon-plus" @click="showAddCarView">
                        添加车辆
                    </el-button>
                </div>
            </div>
            <transition name="slide-fade">
                <div v-show="showAdvanceSearchView"
                     style="border: 1px solid #409eff;border-radius: 5px;box-sizing: border-box;padding: 5px;margin: 10px 0px;">
                    <el-row>
                        <el-col :span="5">
                            车主姓名:
                            <el-input v-model="searchValue.carmaster" placeholder="车主姓名" size="mini"
                                      style="width: 130px;"/>

                        </el-col>
                        <el-col :span="4">
                            车辆品牌:
                            <el-input v-model="searchValue.brand" placeholder="车辆品牌" size="mini" style="width: 130px;"/>
                        </el-col>
                        <el-col :span="4">
                            车辆型号:
                            <el-input v-model="searchValue.model" placeholder="车辆型号" size="mini" style="width: 130px;"/>
                        </el-col>
                    </el-row>
                    <el-row style="margin-top: 10px">
                        <el-col :span="5" :offset="4">
                            <el-button size="mini">取消</el-button>
                            <el-button size="mini" icon="el-icon-search" type="primary" @click="initCars('advanced')">
                                搜索
                            </el-button>
                        </el-col>
                    </el-row>
                </div>
            </transition>
        </div>
        <div style="margin-top: 10px">
            <el-table
                    :data="cars"
                    stripe
                    border
                    v-loading="loading"
                    element-loading-text="正在加载..."
                    element-loading-spinner="el-icon-loading"
                    element-loading-background="rgba(0, 0, 0, 0.8)"
                    style="width: 100%">
                <el-table-column
                        type="selection"
                        width="55">
                </el-table-column>
                <el-table-column
                        prop="id"
                        align="left"
                        fixed
                        label="车辆ID"
                        width="170">
                </el-table-column>
                <el-table-column
                        prop="brand"
                        label="品牌"
                        fixed
                        align="left"
                        width="85">
                </el-table-column>
                <el-table-column
                        prop="model"
                        label="型号"
                        align="left"
                        width="85">
                </el-table-column>
                <el-table-column
                        prop="address"
                        width="150"
                        align="left"
                        label="地址">
                </el-table-column>
                <el-table-column
                        prop="telphone"
                        width="120"
                        align="left"
                        label="联系电话">
                </el-table-column>
                <el-table-column
                        prop="carmaster"
                        width="70"
                        label="车辆主人">
                </el-table-column>
                <el-table-column
                        prop="price"
                        width="140"
                        label="价值">
                </el-table-column>
                <el-table-column
                        prop="enginenum"
                        width="250"
                        label="车架号">
                </el-table-column>
                <el-table-column
                        prop="outdanger"
                        width="250"
                        label="出险次数">
                </el-table-column>
                <el-table-column
                        prop="createTime"
                        width="160"
                        label="录入时间">
                </el-table-column>
                <el-table-column
                        prop="createBy"
                        width="180"
                        align="left"
                        label="录入人">
                </el-table-column>
                <el-table-column
                        prop="updateTime"
                        width="160"
                        label="最近更新时间">
                </el-table-column>
                <el-table-column
                        prop="updateBy"
                        width="160"
                        label="更新人">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        width="200"
                        label="操作">
                    <template slot-scope="scope">
                        <el-button @click="showEditCarView(scope.row)" style="padding: 3px" size="mini">编辑</el-button>
                        <el-button style="padding: 3px" size="mini" type="primary">查看投保</el-button>
                        <el-button @click="deleteCar(scope.row)" style="padding: 3px" size="mini" type="danger">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div style="display: flex;justify-content: flex-end">
                <el-pagination
                        background
                        @current-change="currentChange"
                        @size-change="sizeChange"
                        layout="sizes, prev, pager, next, jumper, ->, total, slot"
                        :total="total">
                </el-pagination>
            </div>
        </div>
        <el-dialog
                :title="title"
                :visible.sync="dialogVisible"
                width="70%">
            <div>
                <el-form :model="car" :rules="rules" ref="carForm">
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="车辆品牌:" prop="brand">
                                <el-input size="mini" style="width: 120px" prefix-icon="el-icon-edit"
                                          v-model="car.brand"
                                          placeholder="请输入车辆品牌"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item label="型号:" prop="model">
                                <el-input size="mini" style="width: 120px" prefix-icon="el-icon-edit"
                                          v-model="car.model"
                                          placeholder="请输入车辆型号"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item label="联系电话:" prop="telphone">
                                <el-input size="mini" style="width: 120px" prefix-icon="el-icon-edit"
                                          v-model="car.telphone"
                                          placeholder="请输入联系电话"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item label="车主姓名:" prop="carmaster">
                                <el-input size="mini" style="width: 120px" prefix-icon="el-icon-edit"
                                          v-model="car.carmaster"
                                          placeholder="请输入车主姓名"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="地址:" prop="address">
                                <el-input size="mini" style="width: 120px" prefix-icon="el-icon-edit"
                                          v-model="car.address"
                                          placeholder="请输入地址"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="车辆价值:" prop="price">
                                <el-input size="mini" style="width: 120px" prefix-icon="el-icon-edit"
                                          v-model="car.price"
                                          placeholder="请输入地址"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="车架号:" prop="enginenum">
                                <el-input size="mini" style="width: 120px" prefix-icon="el-icon-edit"
                                          v-model="car.enginenum"
                                          placeholder="请输入车架号"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="出险次数:" prop="outdanger">
                                <el-input size="mini" style="width: 120px" prefix-icon="el-icon-edit"
                                          v-model="car.outdanger"
                                          placeholder="请输入出险次数"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
            </div>
            <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="doAddCar">确 定</el-button>
  </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "CarInfo",
        data() {
            return {
                searchValue: {
                    brand: null,
                    model: null,
                    carmaster: null,
                    address: null
                },
                title: '',
                importDataBtnIcon: 'el-icon-upload2',
                importDataDisabled: false,
                showAdvanceSearchView: false,
                allDeps: [],
                cars: [],
                loading: false,
                popVisible: false,
                popVisible2: false,
                dialogVisible: false,
                total: 0,
                page: 1,
                keyword: '',
                size: 10,
                nations: [],
                joblevels: [],
                politicsstatus: [],
                positions: [],
                tiptopDegrees: ['本科', '大专', '硕士', '博士', '高中', '初中', '小学', '其他'],
                options: [{
                    value: '选项1',
                    label: '黄金糕'
                }, {
                    value: '选项2',
                    label: '双皮奶'
                }, {
                    value: '选项3',
                    label: '蚵仔煎'
                }, {
                    value: '选项4',
                    label: '龙须面'
                }, {
                    value: '选项5',
                    label: '北京烤鸭'
                }],
                inputDepName: '所属部门',
                car: {
                    id: "",
                    brand: "宝马",
                    model: "X5",
                    address: "浙江绍兴",
                    telphone: 12312123,
                    carmaster: "李四",
                    price: 0.00,
                    enginenum: "FSD123213",
                    outdanger:0
                },
                defaultProps: {
                    children: 'children',
                    label: 'name'
                },
                rules: {
                    brand: [{required: true, message: '请输入车辆品牌', trigger: 'blur'}],
                    model: [{required: true, message: '请输入车辆型号', trigger: 'blur'}],
                    telphone: [{required: true, message: '请输入电话号码', trigger: 'blur'}],
                    address: [{required: true, message: '请输入车主联系地址', trigger: 'blur'}],
                    carmaster: [{required: true, message: '请输入车主姓名', trigger: 'blur'}],
                    price: [{required: true, message: '请输入车辆价值', trigger: 'blur'}],
                    enginenum: [{required: true, message: '请输入车架号', trigger: 'blur'}],
                }
            }
        },
        mounted() {
            this.initCars();
            // this.initData();
            // this.initPositions();
        },
        methods: {
            searvhViewHandleNodeClick(data) {
                this.inputDepName = data.name;
                this.searchValue.departmentId = data.id;
                this.popVisible2 = !this.popVisible2
            },
            onError(err, file, fileList) {
                this.importDataBtnText = '导入数据';
                this.importDataBtnIcon = 'el-icon-upload2';
                this.importDataDisabled = false;
            },
            onSuccess(response, file, fileList) {
                this.importDataBtnText = '导入数据';
                this.importDataBtnIcon = 'el-icon-upload2';
                this.importDataDisabled = false;
                this.initEmps();
            },
            beforeUpload() {
                this.importDataBtnText = '正在导入';
                this.importDataBtnIcon = 'el-icon-loading';
                this.importDataDisabled = true;
            },
            exportData() {
                window.open('/employee/basic/export', '_parent');
            },
            emptyCar() {
                this.car = {
                    id: "",
                    brand: "",
                    model: "",
                    address: "",
                    telphone: null,
                    carmaster: "",
                    price: 0.00,
                    enginenum: "",
                }
            },
            showEditCarView(data) {
                // this.initPositions();
                this.title = '编辑车辆信息';
                this.car = data;
                this.dialogVisible = true;
            },
            deleteCar(data) {
                this.$confirm('此操作将永久删除【' + data.id + '】, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/car/basic/" + data.id).then(resp => {
                        if (resp) {
                            this.initCars();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            doAddCar() {
                if (this.car.id) {
                    this.$refs['carForm'].validate(valid => {
                        if (valid) {
                            this.putRequest("/car/basic/", this.car).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initCars();
                                }
                            })
                        }
                    });
                } else {
                    this.$refs['carForm'].validate(valid => {
                        if (valid) {
                            this.postRequest("/car/basic/", this.car).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initCars();
                                }
                            })
                        }
                    });
                }
            },
            handleNodeClick(data) {
                this.inputDepName = data.name;
                this.emp.departmentId = data.id;
                this.popVisible = !this.popVisible
            },
            showDepView() {
                this.popVisible = !this.popVisible
            },
            showDepView2() {
                this.popVisible2 = !this.popVisible2
            },
            initPositions() {
                this.getRequest('/employee/basic/positions').then(resp => {
                    if (resp) {
                        this.positions = resp;
                    }
                })
            },
            getMaxWordID() {
                this.getRequest("/employee/basic/maxWorkID").then(resp => {
                    if (resp) {
                        this.emp.workID = resp.obj;
                    }
                })
            },
            initData() {
                if (!window.sessionStorage.getItem("nations")) {
                    this.getRequest('/car/basic/nations').then(resp => {
                        if (resp) {
                            this.nations = resp;
                            window.sessionStorage.setItem("nations", JSON.stringify(resp));
                        }
                    })
                } else {
                    this.nations = JSON.parse(window.sessionStorage.getItem("nations"));
                }
                if (!window.sessionStorage.getItem("joblevels")) {
                    this.getRequest('/employee/basic/joblevels').then(resp => {
                        if (resp) {
                            this.joblevels = resp;
                            window.sessionStorage.setItem("joblevels", JSON.stringify(resp));
                        }
                    })
                } else {
                    this.joblevels = JSON.parse(window.sessionStorage.getItem("joblevels"));
                }
                if (!window.sessionStorage.getItem("politicsstatus")) {
                    this.getRequest('/employee/basic/politicsstatus').then(resp => {
                        if (resp) {
                            this.politicsstatus = resp;
                            window.sessionStorage.setItem("politicsstatus", JSON.stringify(resp));
                        }
                    })
                } else {
                    this.politicsstatus = JSON.parse(window.sessionStorage.getItem("politicsstatus"));
                }
                if (!window.sessionStorage.getItem("deps")) {
                    this.getRequest('/employee/basic/deps').then(resp => {
                        if (resp) {
                            this.allDeps = resp;
                            window.sessionStorage.setItem("deps", JSON.stringify(resp));
                        }
                    })
                } else {
                    this.allDeps = JSON.parse(window.sessionStorage.getItem("deps"));
                }
            },
            sizeChange(currentSize) {
                this.size = currentSize;
                this.initEmps();
            },
            currentChange(currentPage) {
                this.page = currentPage;
                this.initEmps();
            },
            showAddCarView() {
                this.emptyCar();
                this.title = '添加车辆';
                this.getMaxWordID();
                this.dialogVisible = true;
            },
            initCars(type) {
                this.loading = true;
                let url = '/car/basic/?page=' + this.page + '&size=' + this.size;
                if (type && type === 'advanced') {
                    if (this.searchValue.brand) {
                        url += '&brand=' + this.searchValue.brand;
                    }
                    if (this.searchValue.model) {
                        url += '&model=' + this.searchValue.model;
                    }
                    if (this.searchValue.address) {
                        url += '&address=' + this.searchValue.address;
                    }
                    if (this.searchValue.carmaster) {
                        url += '&carmaster=' + this.searchValue.carmaster;
                    }
                } else {
                    url += "&brand=" + this.keyword;
                }
                this.getRequest(url).then(resp => {
                    this.loading = false;
                    if (resp) {
                        this.cars = resp.data;
                        this.total = resp.total;
                    }
                });
            }
        }
    }
</script>

<style>
    /* 可以设置不同的进入和离开动画 */
    /* 设置持续时间和动画函数 */
    .slide-fade-enter-active {
        transition: all .8s ease;
    }

    .slide-fade-leave-active {
        transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
    }

    .slide-fade-enter, .slide-fade-leave-to
        /* .slide-fade-leave-active for below version 2.1.8 */
    {
        transform: translateX(10px);
        opacity: 0;
    }
</style>