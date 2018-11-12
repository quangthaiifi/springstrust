<%@ page contentType="text/html; charset = UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCUMENT html>
<html>
<head>
    <title>Chart PA </title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="https://code.highcharts.com/highcharts.js"></script>
    <script src="https://momentjs.com/downloads/moment.js"></script>
    <script type="application/javascript" src="js/script.js"></script>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<script type="text/javascript">
    paValue0='';
    paValue1='';
    paValue2='';
    data
    function runajax() {
        $.ajax({
            url: "draw",
            type: "GET",
            success: function (data) {
                paValue0 = data.paValue0.split(',')

                paValue0[0] = paValue0[0].slice(1, paValue0[0].length)
                for (i = 0; i < paValue0.length; i++) {
                    paValue0[i] = parseFloat(paValue0[i])
                }

                paValue1 = data.paValue1.split(',')
                paValue1[0] = paValue1[0].slice(1, paValue1[0].length)
                for (i = 0; i < paValue1.length; i++) {
                    paValue1[i] = parseFloat(paValue1[i])
                }
                paValue2 = data.paValue2.split(',')
                paValue2[0] = paValue2[0].slice(1, paValue2[0].length)
                for (i = 0; i < paValue2.length; i++) {
                    paValue2[i] = parseFloat(paValue2[i])
                }
                draw('container', 'column', paValue0, paValue1, paValue2, "", "", "")
            }
        });
    }
</script>
<body onload="runajax();">

<script>
    var chara

    function draw(container, type, data0, data1, data2, date0, date1, date2) {

        if (date0 == "") {
            date0 = 2016,
                date1 = 9,
                date2 = 23
        }
        chara = Highcharts.chart(container, {
            chart: {
                type: type
            },
            title: {
                text: 'Weekly PA '
            },


            subtitle: {
                text: '(W43 24/10/2016-30/10/2016)'
            }
            ,
            <%--xAxis: [{--%>
            <%--type: 'datetime',--%>
            <%--categories: <s:property value = "daya" escapeHtml="false"/>,--%>

            <%--}],--%>

            xAxis: {
                startOfWeek: 0, type: 'datetime',
                labels: {
                    formatter:
                        function () {
                            return Highcharts.dateFormat('%a', this.value);
                        }
                }
            },
            tooltip: {
                formatter: function () {
                    var s = moment(this.x).format('llll').split(',')[0] + '<b> (' + moment(this.x).format('DD/MM/YYYY') + ')</b>';

                    $.each(this.points, function () {
                        s += '<br/> <b style=" font-weight: bold; color:' + this.color + '">' + this.series.name + '</b>:<b>' +
                            this.y + 'kW </b>'
                    });

                    return s;
                },
                shared: true
            },
            plotOptions: {
                series: {
                    cursor: 'pointer',
                    point: {
                        events: {
                            click: function () {
                                dates = this.colorIndex.toString() + '/' + moment(this.x).format('DD/MM/YYYY').toString();
                                $.ajax({
                                    url: "draw1?day=/" + dates,
                                    type: "GET",
                                    success: function (data) {
                                        console.log(data)
                                        pa = JSON.stringify(data.paOfDay).split(",")
                                        psp = JSON.stringify(data.psp).split(',')
                                        psp[0] = psp[0].slice(2, psp[0].length)
                                        for (i = 0; i < psp.length; i++) {
                                            psp[i] = parseFloat(psp[i])
                                        }
                                        pa[0] = pa[0].slice(2, pa[0].length);
                                        console.log(pa[0])
                                        for (i = 0; i < pa.length; i++) {
                                            pa[i] = parseFloat(pa[i])
                                        }
                                        draw1('container1', pa, psp)
                                    }

                                })
                            }
                        }
                    }
                }
            },
            yAxis: {
                title: {
                    text: 'Puissance appeieese(kW)'
                }
            },
            series: [{

                id: '1',
                name: 'ENR062A3',
                data: data0,
                pointStart: Date.UTC(date0, date1, date2),
                pointInterval: 24 * 3600 * 1000,


            }, {
                name: 'ENR04CA0',
                data: data1,
                pointStart: Date.UTC(date0, date1, date2),
                pointInterval: 24 * 3600 * 1000
            }, {
                name: 'ENR077A9',
                data: data2,
                pointStart: Date.UTC(date0, date1, date2),
                pointInterval: 24 * 3600 * 1000
            }]
        })
    }
</script>
<script type="text/javascript">

    function checkclicker1(idseria) {

        if (checkbox0.checked) {
            window.chara.get('1').remove()

        } else {
            chara.addSero
            chara.addSeries({
                id: '1',
                name: 'ENR062A3',

                data: pava,
                pointStart: Date.UTC(date0, date1, date2),
                pointInterval: 24 * 3600 * 1000
            });

        }
    }
</script>
<div id="module" style="height:1200px;background: green;width: 20%;float:left">
    <h2 style="margin: 40px; border-bottom: solid 1px silver;text-align: center;font-size: 40px">Modules</h2>

    <div class="round">
        <input id="checkbox0" type="checkbox" onclick="checkclicker1('0')"/>
        <label for="checkbox0"><span class="round"> ENR062A3</span></label>
    </div>
    <div class="round">
        <input id="checkbox1" type="checkbox" onclick="checkclicker2('1')"/>
        <label for="checkbox1"><span class="round"> ENR04CA0</span></label>
    </div>
    <div class="round">
        <input id="checkbox2" type="checkbox" onclick="checkclicker3('2')"/>
        <label for="checkbox2"><span class="round"> ENR077A9</span></label>
    </div>
</div>
</div>
<script type="text/javascript">
    date0='';
        date1='';
        date2='';
    function getValue() {
        var startday = document.getElementById('startDay').value.toString();

        var endDay = document.getElementById('endDay').value;
        if (startday != "" && endDay != "") {
            $.ajax({
                url: "draw?day=/" + startday + "/" + endDay,
                type: "GET",
                success: function (data) {
                    day = data.day
                    day1 = day.split('/')[1].split('-')
                    console.log(day1)
                    date0 = day1[0]
                    date1 = day1[1] - 1
                    date2 = day1[2]
                    paValue0 = data.paValue0.split(',')

                    paValue0[0] = paValue0[0].slice(1, paValue0[0].length)
                    for (i = 0; i < paValue0.length; i++) {
                        paValue0[i] = parseFloat(paValue0[i])
                    }

                    paValue1 = data.paValue1.split(',')
                    paValue1[0] = paValue1[0].slice(1, paValue1[0].length)
                    for (i = 0; i < paValue1.length; i++) {
                        paValue1[i] = parseFloat(paValue1[i])
                    }
                    paValue2 = data.paValue2.split(',')
                    paValue2[0] = paValue2[0].slice(1, paValue2[0].length)
                    for (i = 0; i < paValue2.length; i++) {
                        paValue2[i] = parseFloat(paValue2[i])
                    }

                    draw('container', 'column', paValue0, paValue1, paValue2, date0, date1, date2)
                }
            });
        }
    }
</script>
<div id="bieudo" style="float: right;width: 80%">
    <div class="day">
        <label for="startDay"> From </label>
        <input type="date" name="startDay" id="startDay" onchange="getValue();" value="2016-10-23">
        <label for="endDay"> To </label>
        <input type="date" name="startDay" id="endDay" onchange="getValue();"  value="2016-10-30">
    </div>
    <div id="container" style="width:100%; height:600px;">
    </div>
    <div id="container1" style="width:100%; height:600px;"></div>
</div>


</body>
</html>