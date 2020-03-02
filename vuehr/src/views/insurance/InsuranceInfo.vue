<template>
    <div>
        <div>
            <div style="display: flex;justify-content: space-between">
                <div>
                    <el-input placeholder="请输入险种名称进行搜索，可以直接回车搜索..." prefix-icon="el-icon-search"
                              clearable
                              @clear="initInsurances"
                              style="width: 350px;margin-right: 10px" v-model="keyword"
                              @keydown.enter.native="initCars" :disabled="showAdvanceSearchView"></el-input>
                    <el-button icon="el-icon-search" type="primary" @click="initInsurances" :disabled="showAdvanceSearchView">
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
                        添加险种
                    </el-button>
                </div>
            </div>
            <transition name="slide-fade">
                <div v-show="showAdvanceSearchView"
                     style="border: 1px solid #409eff;border-radius: 5px;box-sizing: border-box;padding: 5px;margin: 10px 0px;">
                    <el-row>
                        <el-col :span="5">
                            险种名称:
                            <el-input v-model="searchValue.carmaster" placeholder="险种名称" size="mini"
                                      style="width: 130px;"/>

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
                    :data="insurances"
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
                        label="保险ID"
                        width="170">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="险种名称"
                        fixed
                        align="left"
                        width="85">
                </el-table-column>
                <el-table-column
                        prop="desc"
                        label="险种描述"
                        align="left"
                        width="200">
                </el-table-column><el-table-column
                        prop="price"
                        label="价格"
                        align="left"
                        width="90">
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
                        <el-button @click="showEditInsView(scope.row)" style="padding: 3px" size="mini">编辑</el-button>
                        <el-button @click="deleteInsurance(scope.row)" style="padding: 3px" size="mini" type="danger">删除
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
                width="60%">
            <div>
                <el-form :model="insurance" :rules="rules" ref="insForm">
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="险种名称:" prop="name">
                                <el-input size="mini" style="width: 120px" prefix-icon="el-icon-edit"
                                          v-model="insurance.name"
                                          placeholder="请输入险种名称"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="价格:" prop="price">
                                <el-input size="mini" style="width: 120px" prefix-icon="el-icon-edit"
                                          v-model="insurance.price"
                                          placeholder="请输入价格"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="险种描述:" prop="desc">
                                <el-input type=“textarea”   prefix-icon="el-icon-edit"
                                          :rows="2"
                                          v-model="insurance.desc"
                                          placeholder="请输入险种描述"></el-input>
                            </el-form-item>
                        </el-col>

                    </el-row>
                </el-form>
            </div>
            <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="doAddInsurance">确 定</el-button>
  </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "InsuranceInfo",
        data() {
            return {
                searchValue: {
                    name:null
                },
                title: '',
                importDataBtnIcon: 'el-icon-upload2',
                importDataDisabled: false,
                showAdvanceSearchView: false,
                allDeps: [],
                insurances: [],
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
                insurance: {
                    id: "",
                    name: "宝马",
                    desc: "X5",
                    price:0.00
                },
                defaultProps: {
                    children: 'children',
                    label: 'name'
                },
                rules: {
                    name: [{required: true, message: '请输入险种名称', trigger: 'blur'}],
                    desc: [{required: true, message: '请输入险种描述', trigger: 'blur'}],

                }
            }
        },
        mounted() {
            this.initInsurances();
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
            emptyInsurance() {
                this.insurance = {
                    id: "",
                    name: "",
                    desc: "",
                    price: 0.00,
                }
            },
            showEditInsView(data) {
                // this.initPositions();
                this.title = '编辑险种信息';
                this.insurance = data;
                this.dialogVisible = true;
            },
            deleteInsurance(data) {
                this.$confirm('此操作将永久删除【' + data.id + '】, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/insurance/basic/" + data.id).then(resp => {
                        if (resp) {
                            this.initInsurances();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            doAddInsurance() {
                if (this.insurance.id) {
                    this.$refs['insForm'].validate(valid => {
                        if (valid) {
                            this.putRequest("/insurance/basic/", this.insurance).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initInsurances();
                                }
                            })
                        }
                    });
                } else {
                    this.$refs['insForm'].validate(valid => {
                        if (valid) {
                            this.postRequest("/insurance/basic/", this.insurance).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initInsurances();
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
                this.emptyInsurance();
                this.title = '添加险种';
                this.dialogVisible = true;
            },
            initInsurances(type) {
                this.loading = true;
                let url = '/insurance/basic/?page=' + this.page + '&size=' + this.size;
                if (type && type === 'advanced') {
                    if (this.searchValue.name) {
                        url += '&name=' + this.searchValue.name;
                    }
                } else {
                    url += "&name=" + this.keyword;
                }
                this.getRequest(url).then(resp => {
                    this.loading = false;
                    if (resp) {
                        this.insurances = resp.data;
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