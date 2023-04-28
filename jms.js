/*
 * 由@neptuno编写的流量信息获取脚本
*/

(async () => {
  let args = getArgs();
  // let info = await getDataInfo(args.url);
  // if (!info) $done();
  // let resetDayLeft = data.bw_reset_day_of_month

//   let used = info.bw_counter_b
//   let total = info.monthly_bw_limit_b;
// //   let expire = args.expire || info.expire;
//   let content = [`已用：${toPercent(used, total)} \t|  剩余：${toMultiply(total, used)}`];

//   content.push(`重置：${resetDayLeft}天`);

//   let now = new Date();
//   let hour = now.getHours();
//   let minutes = now.getMinutes();
//   hour = hour > 9 ? hour : "0" + hour;
//   minutes = minutes > 9 ? minutes : "0" + minutes;

  $done({
    title: `${args.title} `,
    content: 1111,
    icon: args.icon || "airplane.circle",
    "icon-color": args.color || "#007aff",
  });
})();

function getArgs() {
  return Object.fromEntries(
    $argument
      .split("&")
      .map((item) => item.split("="))
      .map(([k, v]) => [k, decodeURIComponent(v)])
  );
}

function getUserInfo(url) {
  let request = { headers: { "User-Agent": "surge" }, url };
  return new Promise((resolve, reject) =>
    $httpClient.get(request, (err, resp) => {
      if (err != null) {
        reject(err);
        return;
      }
      if (resp.status !== 200) {
        reject(resp.status);
        return;
      }
      if (header) {
        resolve(resp.data);
        return;
      }
      reject("链接响应头不带有流量信息");
    })
  );
}

async function getDataInfo(url) {
  const [err, data] = await getUserInfo(url)
    .then((data) => [null, data])
    .catch((err) => [err, null]);
  if (err) {
    console.log(err);
    return;
  }

  return JSON.parse(data);
}

function getRmainingDays(resetDay) {
  if (!resetDay) return;

  let now = new Date();
  let today = now.getDate();
  let month = now.getMonth();
  let year = now.getFullYear();
  let daysInMonth;

  if (resetDay > today) {
    daysInMonth = 0;
  } else {
    daysInMonth = new Date(year, month + 1, 0).getDate();
  }

  return daysInMonth - today + resetDay;
}

function bytesToSize(bytes) {
  if (bytes === 0) return "0B";
  let k = 1024;
  sizes = ["B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"];
  let i = Math.floor(Math.log(bytes) / Math.log(k));
  return (bytes / Math.pow(k, i)).toFixed(2) + " " + sizes[i];
}

function bytesToSizeNumber(bytes) {
  if (bytes === 0) return "0";
  let k = 1024;
  let i = Math.floor(Math.log(bytes) / Math.log(k));
  return (bytes / Math.pow(k, i)).toFixed(2);
}

function toPercent(num, total) {
  return (Math.round((num / total) * 10000) / 100).toFixed(1) + "%";
}


function toMultiply(total, num) {
  let totalDecimalLen, numDecimalLen, maxLen, multiple;
  try {
    totalDecimalLen = total.toString().split(".").length;
  } catch (e) {
    totalDecimalLen = 0;
  }
  try {
    numDecimalLen = num.toString().split(".").length;
  } catch (e) {
    numDecimalLen = 0;
  }
  maxLen = Math.max(totalDecimalLen, numDecimalLen);
  multiple = Math.pow(10, maxLen);
  const numberSize = ((total * multiple - num * multiple) / multiple).toFixed(maxLen);
  return bytesToSize(numberSize);
}

function formatTime(time) {
  let dateObj = new Date(time);
  let year = dateObj.getFullYear();
  let month = dateObj.getMonth() + 1;
  let day = dateObj.getDate();
  return "到期：" + year + "." + month + "." + day + " ";
}
