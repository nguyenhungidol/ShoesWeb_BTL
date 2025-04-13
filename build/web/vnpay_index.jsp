<%-- 
    Document   : index
    Created on : Sep 29, 2015, 7:23:18 PM
    Author     : xonv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tạo mới đơn hàng</title>

    <link href="/vnpay_jsp/assets/bootstrap.min.css" rel="stylesheet"/>
    <link href="/vnpay_jsp/assets/jumbotron-narrow.css" rel="stylesheet">      
    <script src="/vnpay_jsp/assets/jquery-1.11.3.min.js"></script>
</head>

<body>
<div class="container">
    <div class="header clearfix">
        <h3 class="text-muted">VNPAY DEMO</h3>
    </div>

    <h3>Tạo mới đơn hàng</h3>
    <div class="table-responsive">
        <form action="/vnpay_jsp/vnpayajax" id="frmCreateOrder" method="post">        
            <!-- Loại hàng hóa -->
            <div class="form-group">
                <label for="language">Loại hàng hóa </label>
                <select name="ordertype" id="ordertype" class="form-control">
                    <option value="topup">Nạp tiền điện thoại</option>
                    <option value="billpayment">Thanh toán hóa đơn</option>
                    <option value="fashion">Thời trang</option>
                </select>
            </div>

            <!-- Số tiền -->
            <div class="form-group">
                <label for="amount">Số tiền</label>
                <input class="form-control" id="amount" name="amount" type="number" value="10000" />
            </div>

            <!-- Nội dung thanh toán -->
            <div class="form-group">
                <label for="OrderDescription">Nội dung thanh toán</label>
                <textarea class="form-control" id="vnp_OrderInfo" name="vnp_OrderInfo" rows="2">Thanh toan don hang test</textarea>
            </div>

            <!-- Ngân hàng -->
            <div class="form-group">
                <label for="bankcode">Ngân hàng</label>
                <select name="bankcode" id="bankcode" class="form-control">
                    <option value="">Không chọn </option>
                    <option value="NCB">Ngân hàng NCB</option>
                    <option value="SACOMBANK">Ngân hàng SacomBank</option>
                    <option value="VISA">Thanh toán qua VISA/MASTER</option>
                    <option value="VNMART">Ví điện tử VnMart</option>
                    <option value="VIETCOMBANK">Ngân hàng VCB</option>
                    <option value="BIDV">Ngân hàng BIDV</option>
                    <!-- Bạn có thể thêm các ngân hàng khác tại đây -->
                </select>
            </div>

            <!-- Ngôn ngữ -->
            <div class="form-group">
                <label for="language">Ngôn ngữ</label>
                <select name="language" id="language" class="form-control">
                    <option value="vn">Tiếng Việt</option>
                    <option value="en">English</option>
                </select>
            </div>

            <!-- Thông tin hóa đơn -->
            <div class="form-group">
                <h3>Thông tin hóa đơn (Billing)</h3>
            </div>
            <div class="form-group">
                <label>Họ tên (*)</label>
                <input class="form-control" name="txt_billing_fullname" type="text" value="Nguyen Van A"/>             
            </div>
            <div class="form-group">
                <label>Email (*)</label>
                <input class="form-control" name="txt_billing_email" type="text" value="a@gmail.com"/>   
            </div>
            <div class="form-group">
                <label>Số điện thoại (*)</label>
                <input class="form-control" name="txt_billing_mobile" type="text" value="0909123456"/>   
            </div>
            <div class="form-group">
                <label>Địa chỉ (*)</label>
                <input class="form-control" name="txt_billing_addr1" type="text" value="123 ABC Street"/>   
            </div>
            <div class="form-group">
                <label>Mã bưu điện (*)</label>
                <input class="form-control" name="txt_postalcode" type="text" value="700000"/> 
            </div>
            <div class="form-group">
                <label>Tỉnh/TP (*)</label>
                <input class="form-control" name="txt_bill_city" type="text" value="Ho Chi Minh"/> 
            </div>
            <div class="form-group">
                <label>Quốc gia (*)</label>
                <input class="form-control" name="txt_bill_country" type="text" value="VN"/>
            </div>

            <!-- Nút thanh toán -->
            <button type="submit" class="btn btn-primary">Thanh toán</button>
        </form>
    </div>

    <footer class="footer">
        <p>&copy; VNPAY 2015</p>
    </footer>
</div>  

<!-- VNPAY SDK -->
<link href="https://pay.vnpay.vn/lib/vnpay/vnpay.css" rel="stylesheet" />
<script src="https://pay.vnpay.vn/lib/vnpay/vnpay.min.js"></script>

<!-- Xử lý AJAX tạo đơn hàng -->
<script type="text/javascript">
    $("#frmCreateOrder").submit(function () {
        var postData = $("#frmCreateOrder").serialize();
        var submitUrl = $("#frmCreateOrder").attr("action");

        $.ajax({
            type: "POST",
            url: submitUrl,
            data: postData,
            dataType: 'JSON',
            success: function (x) {
                if (x.code === '00') {
                    alert("Tạo đơn hàng thành công! Đang chuyển hướng đến cổng thanh toán...");
                    if (window.vnpay) {
                        vnpay.open({width: 768, height: 600, url: x.data});
                    } else {
                        location.href = x.data;
                    }
                } else {
                    alert("Lỗi: " + x.Message);
                }
            },
            error: function () {
                alert("Đã có lỗi xảy ra khi tạo đơn hàng.");
            }
        });

        return false; // ngăn form submit mặc định
    });
</script>
</body>
</html>
